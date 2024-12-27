package com.sage_patern.common.events;

public class PaymentFailedEvent {
    private Long orderId;  // Hangi siparişin ödeme işlemi başarısız oldu?

    public PaymentFailedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public PaymentFailedEvent() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "PaymentFailedEvent{orderId=" + orderId + '}';
    }
}
