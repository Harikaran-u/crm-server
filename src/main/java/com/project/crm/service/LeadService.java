package com.project.crm.service;

import com.project.crm.model.Address;
import com.project.crm.model.Customer;
import com.project.crm.model.Lead;
import com.project.crm.repository.LeadJpaRepository;
import com.project.crm.repository.LeadRepository;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeadService implements LeadRepository {

    @Autowired
    private LeadJpaRepository leadJpaRepository;

    @Override
    public List<Lead> getLeads() {
        return leadJpaRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> getLead(Integer id){
        Optional<Lead> lead = leadJpaRepository.findById(id);
        if(lead.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("lead not found.");
        }
        return ResponseEntity.ok(lead.get());
    }

    @Override
    public ResponseEntity<String> addLead(Lead lead) {
        try {
            String email = lead.getEmail();
            Long phone = lead.getPhone();
            Address address = lead.getAddress();

            boolean isValidPhone = ValidationUtils.isValidPhoneNumber(phone);
            boolean isValidEmailId = ValidationUtils.isValidEmail(email);

            boolean isAddressDataNotNull = address != null &&
                    address.getDoorNo() != null &&
                    address.getState() != null &&
                    address.getCity() != null &&
                    address.getDistrict() != null &&
                    address.getPincode() != null &&
                    address.getStreetName() != null;

            if (!isValidEmailId) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide a valid email.");
            }
            if (!isValidPhone) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide a valid phone number.");
            }
            if (!isAddressDataNotNull) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide complete address details.");
            }

            leadJpaRepository.save(lead);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lead created successfully");

        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicated data found. Ensure phone number and email are unique.");

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }

    @Override
    public ResponseEntity<String> updateLead(Integer id, Lead lead) {
        Optional<Lead> leadById = leadJpaRepository.findById(id);

        if(leadById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found");
        }

        Lead currentLead = leadById.get();
        String name = lead.getName();
        String email = lead.getEmail();
        Long phone = lead.getPhone();
        Address presentAddress = lead.getAddress();

        if(name != null && !name.isEmpty()){
            currentLead.setName(name);
        }
        if(email != null && !email.isEmpty()){
            if(!ValidationUtils.isValidEmail(email)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid email to update.");
            }
            currentLead.setEmail(email);
        }
        if(phone != null){
            if(!ValidationUtils.isValidPhoneNumber(phone)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid phone number to update.");
            }
            currentLead.setPhone(phone);
        }
        if(lead.isQualified() != null){
            currentLead.setQualified(lead.isQualified());
        }
        if(presentAddress != null) {
            Address currentAddress = currentLead.getAddress();
            if(!ValidationUtils.updateAddress(currentAddress, presentAddress)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide valid pincode.");
            }
        }

        try{
            leadJpaRepository.save(currentLead);
            return ResponseEntity.ok("Lead updated successfully");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data duplication found. Please kindly provide valid data.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try after sometime.");
        }
    }

    @Override
    public ResponseEntity<String> deleteLead(Integer id) {
        Optional<Lead> leadById = leadJpaRepository.findById(id);
        if(leadById.isPresent()){
            leadJpaRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found.");
    }


}
