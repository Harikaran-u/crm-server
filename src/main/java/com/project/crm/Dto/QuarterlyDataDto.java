package com.project.crm.Dto;

public class QuarterlyDataDto {
    private String quarter;
    private String saleValue;

    public QuarterlyDataDto(String quarter, String saleValue) {
        this.quarter = quarter;
        this.saleValue = saleValue;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(String saleValue) {
        this.saleValue = saleValue;
    }
}
