package com.saga_patern.payment_service.dto.response;

public class CreatePaymentResponse {
    private int id;
    private Double amount;

    public CreatePaymentResponse() {
    }

    public CreatePaymentResponse(int id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

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
}
