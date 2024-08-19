package com.project.crm.Dto;

public class FeedbackTrendLineDto {
    private String timePeriod;
    private Double averageRating;

    public FeedbackTrendLineDto(String timePeriod, Double averageRating) {
        this.timePeriod = timePeriod;
        this.averageRating = averageRating;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
