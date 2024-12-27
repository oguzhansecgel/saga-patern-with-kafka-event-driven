package com.saga_patern.order_service.config.kafka;

import com.sage_patern.common.events.OrderCreatedEvent;
import com.sage_patern.common.events.PaymentFailedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaAddress;

    @Value("${my.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, PaymentFailedEvent> consumerFactory() {
        JsonDeserializer<PaymentFailedEvent> jsonDeserializer = new JsonDeserializer<>(PaymentFailedEvent.class);
        jsonDeserializer.addTrustedPackages("*"); // Specify trusted packages
        jsonDeserializer.setUseTypeHeaders(false); // If type headers are not used

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName()); // Use class name

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
