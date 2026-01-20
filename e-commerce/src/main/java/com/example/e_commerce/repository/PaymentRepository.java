package com.example.e_commerce.repository;

import com.example.e_commerce.model.OrderItem;
import com.example.e_commerce.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Optional<Payment> findByOrderId(String orderId);

    Optional<Payment> findByPaymentId(String paymentId);
}
