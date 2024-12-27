package com.saga_patern.payment_service.repository;

import com.saga_patern.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    //void deleteByOrderId(int orderId);
}
