package com.project.crm.repository;

import com.project.crm.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadJpaRepository extends JpaRepository<Lead, Integer> {
}
