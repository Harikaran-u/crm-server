package com.project.crm.repository;

import com.project.crm.model.MonthlySalesTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyTargetJpaRepository extends JpaRepository<MonthlySalesTarget, Integer> {
    List<MonthlySalesTarget> findByYear(Integer year);
}
