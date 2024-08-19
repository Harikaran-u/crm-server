package com.project.crm.Dto;

public class YearlySalesTargetDto {
    private Integer year;
    private Double targetAmount;
    private Double achievedAmount;

    public YearlySalesTargetDto(Integer year, Double targetAmount, Double achievedAmount) {
        this.year = year;
        this.targetAmount = targetAmount;
        this.achievedAmount = achievedAmount;
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

    public Double getAchievedAmount() {
        return achievedAmount;
    }

    public void setAchievedAmount(Double achievedAmount) {
        this.achievedAmount = achievedAmount;
    }
}
