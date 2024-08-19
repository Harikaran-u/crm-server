package com.project.crm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.sql.Timestamp;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "feedback_text")
    private String feedbackText;
    @Column(name="rating")
    @Min(1)
    @Max(5)
    private Integer rating;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)

    private Customer customer;

    public Feedback() {
    }

    public Feedback(Integer id, String feedbackText, Integer rating, Timestamp createdAt, Customer customer) {
        this.id = id;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.createdAt = createdAt;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public @Min(1) @Max(5) Integer getRating() {
        return rating;
    }

    public void setRating(@Min(1) @Max(5) Integer rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
