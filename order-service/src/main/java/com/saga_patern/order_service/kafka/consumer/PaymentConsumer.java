package com.saga_patern.order_service.kafka.consumer;

import com.saga_patern.order_service.service.OrderService;
import com.sage_patern.common.events.OrderCreatedEvent;
import com.sage_patern.common.events.PaymentFailedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private final OrderService orderService;

    public PaymentConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.template.payment-failed-topic}", groupId = "${my.kafka.consumer.group-id}")
    public void consume(PaymentFailedEvent event)
    {
        System.out.println("Payment Failed Event: " + event);
        orderService.deleteOrder(event.getOrderId());
    }
}
