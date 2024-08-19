package com.project.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "lead")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotBlank(message = "Name should not be blank")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Email should not be blank")
    @Column(name = "email", unique = true)
    private String email;
    @NotNull(message = "Phone number should not be blank")
    @Column(name = "phone", unique = true)
    private Long phone;
    @Column(name = "qualified")
    private Boolean qualified = false;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Address should not be blank")
    @JoinColumn(name = "address_id", nullable = false)
    @JsonIgnoreProperties("customer")
    private Address address;

    public Lead() {
    }

    public Lead(Integer id, String name, String email, Long phone, Boolean qualified, Timestamp createdAt, Timestamp updatedAt, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.qualified = qualified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Address should not be blank") Address getAddress() {
        return address;
    }

    public void setAddress(@NotNull(message = "Address should not be blank") Address address) {
        this.address = address;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isQualified() {
        return qualified;
    }

    public void setQualified(Boolean qualified) {
        this.qualified = qualified;
    }

    public @NotBlank(message = "Email should not be blank") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email should not be blank") String email) {
        this.email = email;
    }

    public @NotNull(message = "Phone number should not be blank") Long getPhone() {
        return phone;
    }

    public void setPhone(@NotNull(message = "Phone number should not be blank") Long phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Name should not be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name should not be blank") String name) {
        this.name = name;
    }
}
