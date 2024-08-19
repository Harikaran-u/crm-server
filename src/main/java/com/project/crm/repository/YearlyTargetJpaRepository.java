package com.project.crm.repository;

import com.project.crm.model.YearlySalesTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyTargetJpaRepository extends JpaRepository<YearlySalesTarget, Integer> {
    YearlySalesTarget findByYear(Integer year);
}
