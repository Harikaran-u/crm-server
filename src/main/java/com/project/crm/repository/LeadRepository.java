package com.project.crm.repository;

import com.project.crm.model.Lead;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository {
    List<Lead> getLeads();
    ResponseEntity<Object> getLead(Integer id);
    ResponseEntity<String> addLead(Lead lead);
    ResponseEntity<String> updateLead(Integer id, Lead lead);
    ResponseEntity<String> deleteLead(Integer id);
}
