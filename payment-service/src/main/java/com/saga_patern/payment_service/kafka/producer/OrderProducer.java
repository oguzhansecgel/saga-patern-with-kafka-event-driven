package com.saga_patern.payment_service.kafka.producer;

import com.sage_patern.common.events.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, PaymentFailedEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, PaymentFailedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.template.payment-failed-topic}")
    private String defaultTopic;

    public void sendMessage(PaymentFailedEvent message) {
        System.out.println("Payment Failed Sout: ");
        log.info("Payment Failed Log: ");
        kafkaTemplate.send(defaultTopic, message);

    }
}
