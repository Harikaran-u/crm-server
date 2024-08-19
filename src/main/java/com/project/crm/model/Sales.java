package com.project.crm.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sales{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="product_id")
    private Integer productId;
    @Column(name="customer_id")
    private Integer customerId;
    @Column(name="sale_date")
    private LocalDateTime saleDate;
    @Column(name="sale_amount")
    private Double saleAmount;

    public Sales() {
    }

    public Sales(Integer id, Integer productId, Integer customerId, LocalDateTime saleDate, Double saleAmount) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }
}
