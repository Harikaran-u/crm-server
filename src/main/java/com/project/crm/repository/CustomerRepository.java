package com.project.crm.repository;

import com.project.crm.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository{
    List<Customer> getCustomers();
    ResponseEntity<Object> getCustomerById(Integer id);
    ResponseEntity<String> addCustomer(Customer customer);
    ResponseEntity<String> updateCustomer(Integer id, Customer customer);
    ResponseEntity<String> deleteCustomer(Integer id);
}
