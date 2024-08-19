package com.project.crm.repository;

import com.project.crm.Dto.MonthlySalesTargetDto;
import com.project.crm.Dto.QuarterlyDataDto;
import com.project.crm.Dto.RegionalSalesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesReportsRepository {
    ResponseEntity<Object> getTotalSalesByYear(Integer year);
    ResponseEntity<List<MonthlySalesTargetDto>> getTotalMonthlySalesByYear(Integer year);
    RegionalSalesDto getRegionalSalesStats();
    List<QuarterlyDataDto> generateQuarterlySalesReport();
}
