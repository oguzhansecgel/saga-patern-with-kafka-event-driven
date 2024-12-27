package com.sage_patern.common.events;

public class PaymentSuccessfulEvent {
    private Long orderId;

    public PaymentSuccessfulEvent() {
    }

    public PaymentSuccessfulEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
