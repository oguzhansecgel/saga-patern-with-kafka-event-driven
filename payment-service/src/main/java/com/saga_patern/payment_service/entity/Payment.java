package com.saga_patern.payment_service.entity;


import jakarta.persistence.*;

@Table(name = "payments")
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double amount;
    private String status;
    private Long orderId;
    public Payment() {
    }

    public Payment(int id, Double amount, String status, Long orderId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
    }

    // Getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // Diğer getter ve setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}