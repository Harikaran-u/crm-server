package com.project.crm.controller;

import com.project.crm.Dto.MonthlySalesTargetDto;
import com.project.crm.Dto.QuarterlyDataDto;
import com.project.crm.Dto.RegionalSalesDto;
import com.project.crm.Dto.YearlySalesTargetDto;
import com.project.crm.service.SalesReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://crm-client-eosin.vercel.app/")
@RestController
@RequestMapping("/sales_reports")
public class SalesReportsController {
    @Autowired
    private SalesReportsService salesReportsService;
    @GetMapping("/yearly/{year}")
    public ResponseEntity<Object> getTotalSalesByYear(@PathVariable("year") Integer year){
        return salesReportsService.getTotalSalesByYear(year);
    }

    @GetMapping("/monthly/{year}")
    public ResponseEntity<List<MonthlySalesTargetDto>> getTotalMonthlySalesByYear(@PathVariable("year") Integer year){
        return salesReportsService.getTotalMonthlySalesByYear(year);
    }

    @GetMapping("/regional")
    public RegionalSalesDto getRegionalSalesStats(){
        return salesReportsService.getRegionalSalesStats();
    }

    @GetMapping("/quarterly")
    public List<QuarterlyDataDto> generateQuarterlySalesReport(){
        return salesReportsService.generateQuarterlySalesReport();
    }
}
