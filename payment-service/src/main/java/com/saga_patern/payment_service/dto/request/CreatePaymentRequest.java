package com.saga_patern.payment_service.dto.request;

public class CreatePaymentRequest {

    private Double amount;
    private String status;
    private Long orderId;

    public CreatePaymentRequest() {
    }

    public CreatePaymentRequest(Double amount, String status, Long orderId) {
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
