package com.saga_patern.payment_service.kafka.consumer;

import com.sage_patern.common.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {


     @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreatedEvent event)
     {
         System.out.println("OrderCreatedEvent: " + event);
     }
}
