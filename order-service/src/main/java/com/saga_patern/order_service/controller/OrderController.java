package com.saga_patern.order_service.controller;

import com.saga_patern.order_service.dto.request.CreateOrderRequest;
import com.saga_patern.order_service.dto.response.CreateOrderResponse;
import com.saga_patern.order_service.dto.response.GetAllOrderResponse;
import com.saga_patern.order_service.entity.Order;
import com.saga_patern.order_service.service.OrderService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAll/orders")
    @RateLimiter(name = "default")
    public Page<GetAllOrderResponse> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return orderService.getAllOrderResponse(pageable);
    }
}
