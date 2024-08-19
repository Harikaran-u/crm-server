package com.project.crm.repository;

import com.project.crm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c.id FROM Customer c JOIN c.address a WHERE a.region = :regionValue")
    List<Integer> findCustomersByRegion(@Param("regionValue") String regionValue);
}
