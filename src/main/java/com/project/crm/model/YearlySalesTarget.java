package com.project.crm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "yearly_sales_target")
public class YearlySalesTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "year", unique = true)
    private Integer year;
    @Column(name = "target_amount")
    private Double targetAmount;

    public YearlySalesTarget() {
    }

    public YearlySalesTarget(Integer id, Integer year, Double targetAmount) {
        this.id = id;
        this.year = year;
        this.targetAmount = targetAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
