package com.project.crm.controller;

import com.project.crm.model.Lead;
import com.project.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://crm-client-eosin.vercel.app/")
@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping("/all")
    public List<Lead> getLeads(){
        return leadService.getLeads();
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<Object> getLead(@PathVariable("leadId") Integer id){
        return leadService.getLead(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addLead(@RequestBody Lead lead){
        return leadService.addLead(lead);
    }

    @PutMapping("/update/{leadId}")
    public ResponseEntity<String> updateLead(@PathVariable("leadId") Integer id, @RequestBody Lead lead){
        return leadService.updateLead(id, lead);
    }

    @DeleteMapping("/{leadId}")
    public ResponseEntity<String> deleteLead(@PathVariable("leadId") Integer id){
        return leadService.deleteLead(id);
    }
}
