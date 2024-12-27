package com.saga_patern.order_service.entity;

import jakarta.persistence.*;

@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;

    public Order() {
    }

    public Order(Long orderId, Long productId, Integer quantity, Double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
