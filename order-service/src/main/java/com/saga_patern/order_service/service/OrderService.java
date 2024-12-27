package com.saga_patern.order_service.service;

import com.saga_patern.order_service.dto.request.CreateOrderRequest;
import com.saga_patern.order_service.dto.response.CreateOrderResponse;
import com.saga_patern.order_service.entity.Order;
import com.saga_patern.order_service.kafka.producer.PaymentProducer;
import com.sage_patern.common.events.*;
import com.saga_patern.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentProducer paymentProducer;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, PaymentProducer paymentProducer) {
        this.orderRepository = orderRepository;
        this.paymentProducer = paymentProducer;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setQuantity(request.getQuantity());
        order.setProductId(request.getProductId());
        order.setTotalPrice(request.getTotalPrice());

            Order savedOrder = orderRepository.save(order);
            OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
            orderCreatedEvent.setOrderId(orderCreatedEvent.getOrderId());
            orderCreatedEvent.setQuantity(order.getQuantity());
            orderCreatedEvent.setProductId(order.getProductId());
            orderCreatedEvent.setTotalPrice(order.getTotalPrice());
            paymentProducer.sendMessage(orderCreatedEvent);
            return new CreateOrderResponse(savedOrder.getOrderId(), savedOrder.getProductId(), savedOrder.getQuantity(), savedOrder.getTotalPrice());

    }
    public void deleteOrder(Long id)
    {
        orderRepository.deleteById(id);
    }

}
