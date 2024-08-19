package com.project.crm.repository;


import com.project.crm.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesReportsJpaRepository extends JpaRepository<Sales, Integer> {
    @Query("SELECT COALESCE(SUM(s.saleAmount), 0) FROM Sales s WHERE YEAR(s.saleDate) = :year")
    Double findTotalSalesByYear(@Param("year") Integer year);

    @Query("SELECT COALESCE(SUM(s.saleAmount), 0) FROM Sales s WHERE YEAR(s.saleDate) = :year AND MONTH(s.saleDate) = :month")
    Double findTotalSalesByMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT SUM(s.saleAmount) FROM Sales s WHERE s.customerId IN :customerIds")
    Double findTotalSalesByCustomerIds(@Param("customerIds") List<Integer> customerIds);

    @Query("SELECT s FROM Sales s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    List<Sales> findSalesWithinPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
