package com.project.crm.service;

import com.project.crm.Dto.MonthlySalesTargetDto;
import com.project.crm.Dto.QuarterlyDataDto;
import com.project.crm.Dto.RegionalSalesDto;
import com.project.crm.Dto.YearlySalesTargetDto;
import com.project.crm.model.MonthlySalesTarget;
import com.project.crm.model.Sales;
import com.project.crm.model.YearlySalesTarget;
import com.project.crm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.project.crm.utils.ValidationUtils.*;

@Service
public class SalesReportsService implements SalesReportsRepository {

    @Autowired
    private SalesReportsJpaRepository salesReportsJpaRepository;

    @Autowired
    private YearlyTargetJpaRepository yearlyTargetJpaRepository;

    @Autowired
    private MonthlyTargetJpaRepository monthlyTargetJpaRepository;

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Override
    public ResponseEntity<Object> getTotalSalesByYear(Integer year) {
        try{
            YearlySalesTarget yearlySalesTarget = yearlyTargetJpaRepository.findByYear(year);
            Double targetValue = yearlySalesTarget.getTargetAmount() != null ? yearlySalesTarget.getTargetAmount() : 0.0;
            Double achievedValue = salesReportsJpaRepository.findTotalSalesByYear(year);
            return ResponseEntity.ok (new YearlySalesTargetDto (year, roundToTwoDecimal(targetValue), roundToTwoDecimal(achievedValue)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("yearly sales not found for the given year.");
        }

    }

    @Override
    public ResponseEntity<List<MonthlySalesTargetDto>> getTotalMonthlySalesByYear(Integer year) {
        List<MonthlySalesTargetDto> monthlySalesList = new ArrayList<>();
        List<MonthlySalesTarget> monthlySalesTargetsList = monthlyTargetJpaRepository.findByYear(year);

        monthlySalesTargetsList.forEach(eachMonth -> {
            Double achievedTarget = salesReportsJpaRepository.findTotalSalesByMonth(year, eachMonth.getMonth());
            Double monthlyTarget = monthlySalesTargetsList.stream().filter(each -> Objects.equals(each.getMonth(), eachMonth.getMonth())).findFirst().get().getTargetAmount();
            MonthlySalesTargetDto monthlySalesTarget = new MonthlySalesTargetDto(eachMonth.getMonth() , roundToTwoDecimal(monthlyTarget), roundToTwoDecimal(achievedTarget));
            monthlySalesList.add(monthlySalesTarget);
        });

        if(monthlySalesTargetsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

        return ResponseEntity.ok(monthlySalesList);
    }

    @Override
    public RegionalSalesDto getRegionalSalesStats() {
        String[] regionalArray = {"EAST", "WEST", "NORTH", "SOUTH"};
        HashMap<String, Double> regionalStats = new HashMap<>();
        Arrays.stream(regionalArray).forEach((eachRegion) -> {
            List<Integer> customerIds = customerJpaRepository.findCustomersByRegion(eachRegion);
            Double regionalSum = salesReportsJpaRepository.findTotalSalesByCustomerIds(customerIds);
            regionalStats.put(eachRegion, roundToTwoDecimal(regionalSum));
        });
        Double totalSales = regionalStats.values().stream().reduce(0.0, Double::sum);
        regionalStats.keySet().forEach((each) -> {
            Double percentage = (regionalStats.get(each) / totalSales) * 100;
            regionalStats.put(each, roundToTwoDecimal(percentage));
        });
        return new RegionalSalesDto(regionalStats.get("EAST"), regionalStats.get("WEST"), regionalStats.get("NORTH"), regionalStats.get("SOUTH"));
    }

    @Override
    public List<QuarterlyDataDto> generateQuarterlySalesReport() {
        LocalDateTime startOfYear = getStartOfFinancialYear().atStartOfDay();
        LocalDateTime endOfYear = getEndOfFinancialYear().atTime(23, 59, 59);
        List<Sales> salesList = salesReportsJpaRepository.findSalesWithinPeriod(startOfYear, endOfYear);
        Map<String, Double> salesByQuarter = salesList.stream()
                .collect(Collectors.groupingBy(
                        salesRecord ->  getQuarter(salesRecord.getSaleDate().toLocalDate()),
                        Collectors.summingDouble(Sales::getSaleAmount)
                ));

        return salesByQuarter.entrySet().stream()
                .map(entry -> new QuarterlyDataDto(entry.getKey(), String.format("%.2f", entry.getValue())))
                .collect(Collectors.toList());
    }
}
