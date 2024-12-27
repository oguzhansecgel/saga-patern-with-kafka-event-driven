package com.saga_patern.payment_service.service;


import com.saga_patern.payment_service.dto.request.CreatePaymentRequest;
import com.saga_patern.payment_service.dto.response.CreatePaymentResponse;
import com.saga_patern.payment_service.entity.Payment;
import com.saga_patern.payment_service.kafka.producer.OrderProducer;
import com.saga_patern.payment_service.repository.PaymentRepository;
import com.sage_patern.common.events.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderProducer orderProducer;
    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

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
            if (createPaymentRequest.getAmount()>10) {
                Payment savedPayment = paymentRepository.save(payment);
                logger.info("Payment Created");
                return new CreatePaymentResponse(savedPayment.getId(), savedPayment.getAmount());
            } else {
                PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
                paymentFailedEvent.setOrderId(payment.getOrderId());
                orderProducer.sendMessage(paymentFailedEvent);
                logger.error("Failed to save payment");
                throw new RuntimeException("Failed to save payment");
            }
        } catch (Exception e) {

            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
            paymentFailedEvent.setOrderId(createPaymentRequest.getOrderId());
            orderProducer.sendMessage(paymentFailedEvent);
            logger.error("Failed to save payment", e);
            throw new RuntimeException("Failed to save payment", e);
        }


    }

}
