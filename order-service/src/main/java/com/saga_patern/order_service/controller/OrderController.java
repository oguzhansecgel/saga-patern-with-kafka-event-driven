package com.saga_patern.order_service.controller;

import com.saga_patern.order_service.dto.request.CreateOrderRequest;
import com.saga_patern.order_service.dto.response.CreateOrderResponse;
import com.saga_patern.order_service.entity.Order;
import com.saga_patern.order_service.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/order")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest order)
    {
        return orderService.createOrder(order);
    }
}
