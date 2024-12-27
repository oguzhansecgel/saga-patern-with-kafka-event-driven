package com.saga_patern.payment_service.controller;

import com.saga_patern.payment_service.dto.request.CreatePaymentRequest;
import com.saga_patern.payment_service.dto.response.CreatePaymentResponse;
import com.saga_patern.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping
    public CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest)
    {
        return paymentService.createPayment(createPaymentRequest);
    }
}
