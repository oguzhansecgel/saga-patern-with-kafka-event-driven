package com.saga_patern.order_service.kafka.producer;

import com.sage_patern.common.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProducer.class);
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopic;

    public void sendMessage(OrderCreatedEvent message) {
        System.out.println("Event Created Sout: ");
        log.info("Event Created Log: ");
        kafkaTemplate.send(defaultTopic, message);

    }
}
