package com.project.crm.service;

import com.project.crm.model.Address;
import com.project.crm.model.Customer;
import com.project.crm.repository.CustomerJpaRepository;
import com.project.crm.repository.CustomerRepository;
import com.project.crm.repository.FeedbackJpaRepository;
import com.project.crm.utils.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CustomerRepository {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private FeedbackJpaRepository feedbackJpaRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerJpaRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> getCustomerById(Integer id) {
        Optional<Customer> customer = customerJpaRepository.findById(id);

        if(customer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer not found.");
        }

        return ResponseEntity.ok(customer.get());
    }

    @Override
    public ResponseEntity<String> addCustomer(Customer customer) {
        try{
            String email = customer.getEmail();
            Long phone = customer.getPhone();
            Address address = customer.getAddress();
            boolean isAddressDataNotNull = address.getDoorNo() != null && address.getState() != null && address.getCity() != null && address.getDistrict() != null && address.getPincode() != null && address.getStreetName() != null;
            boolean isValidPhone = ValidationUtils.isValidPhoneNumber(phone);
            boolean isValidEmailId = ValidationUtils.isValidEmail(email);

            if(!isValidEmailId){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please kindly provide valid email.");
            }
            if(!isValidPhone){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please kindly provide valid phone number.");
            }
            if(!isAddressDataNotNull){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please kindly provide valid address details.");
            }
            customerJpaRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
        }catch (ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations =  e.getConstraintViolations();
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicated data found. Check for any duplication in the data and phone number , email should be unique.");
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }

    @Override
    public ResponseEntity<String> updateCustomer(Integer id, Customer customer) {
        Optional<Customer> customerById = customerJpaRepository.findById(id);

        if(customerById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer not found.");
        }

        Customer currentCustomer = customerById.get();
        String name = customer.getName();
        String email = customer.getEmail();
        Long phone = customer.getPhone();
        Address presentAddress = customer.getAddress();

        if(name != null && !name.isEmpty()){
            currentCustomer.setName(name);
        }
        if(email != null && !email.isEmpty()){
            if(!ValidationUtils.isValidEmail(email)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid email to update.");
            }
            currentCustomer.setEmail(email);
        }
        if(phone != null){
            if(!ValidationUtils.isValidPhoneNumber(phone)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid phone number to update.");
            }
            currentCustomer.setPhone(phone);
        }
        if(presentAddress != null) {
            Address currentAddress = currentCustomer.getAddress();
            if(!ValidationUtils.updateAddress(currentAddress, presentAddress)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please kindly provide valid pincode");
            };
        }

        try{
            customerJpaRepository.save(currentCustomer);
            return ResponseEntity.ok("updated successfully.");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data duplication found. Please kindly provide valid data.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try after sometime.");
        }
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        Optional<Customer> customer = customerJpaRepository.findById(id);
        if(customer.isPresent()){
            feedbackJpaRepository.deleteByCustomerId(id);
            customerJpaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

    }


}
