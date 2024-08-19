package com.project.crm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "monthly_sales_target",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"year", "month"})
})
public class MonthlySalesTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "month", nullable = false)
    private Integer month;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    public MonthlySalesTarget() {
    }

    public MonthlySalesTarget(Integer id, Integer month, Integer year, Double targetAmount) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.targetAmount = targetAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }
}
