package com.project.crm.Dto;

public class MonthlySalesTargetDto {
    private Integer month;
    private Double targetAmount;
    private Double achievedAmount;

    public MonthlySalesTargetDto(Integer month, Double targetAmount, Double achievedAmount) {
        this.month = month;
        this.targetAmount = targetAmount;
        this.achievedAmount = achievedAmount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
