package com.saga_patern.payment_service.service;


import com.saga_patern.payment_service.dto.request.CreatePaymentRequest;
import com.saga_patern.payment_service.dto.response.CreatePaymentResponse;
import com.saga_patern.payment_service.entity.Payment;
import com.saga_patern.payment_service.kafka.producer.OrderProducer;
import com.saga_patern.payment_service.repository.PaymentRepository;
import com.sage_patern.common.events.PaymentFailedEvent;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderProducer orderProducer;


    public PaymentService(PaymentRepository paymentRepository, OrderProducer orderProducer) {
        this.paymentRepository = paymentRepository;
        this.orderProducer = orderProducer;
    }

    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest)
    {
        Payment payment = new Payment();
        payment.setAmount(createPaymentRequest.getAmount());
        payment.setStatus(createPaymentRequest.getStatus());
        payment.setOrderId(createPaymentRequest.getOrderId());
        try {
            Payment savedPayment = paymentRepository.save(payment);

            if (createPaymentRequest.getAmount()>10) {
                return new CreatePaymentResponse(savedPayment.getId(), savedPayment.getAmount());
            } else {
                PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
                paymentFailedEvent.setOrderId(payment.getOrderId());
                orderProducer.sendMessage(paymentFailedEvent);
                throw new RuntimeException("Failed to save payment");
            }
        } catch (Exception e) {

            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
            paymentFailedEvent.setOrderId(createPaymentRequest.getOrderId());
            orderProducer.sendMessage(paymentFailedEvent);
          throw new RuntimeException("Failed to save payment", e);
        }


    }

}
