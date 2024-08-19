package com.project.crm.Dto;

public class FeedbackEmotionsDto {
    private Double good;
    private Double bad;
    private Double average;

    public FeedbackEmotionsDto(Double good, Double bad, Double average) {
        this.good = good;
        this.bad = bad;
        this.average = average;
    }

    public Double getGood() {
        return good;
    }

    public void setGood(Double good) {
        this.good = good;
    }

    public Double getBad() {
        return bad;
    }

    public void setBad(Double bad) {
        this.bad = bad;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
