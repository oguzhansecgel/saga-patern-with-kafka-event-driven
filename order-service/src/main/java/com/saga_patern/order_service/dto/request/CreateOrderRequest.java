package com.saga_patern.order_service.dto.request;

public class CreateOrderRequest {

    private Long productId;
    private Integer quantity;
    private Double totalPrice;


    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long productId, Integer quantity, Double totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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
