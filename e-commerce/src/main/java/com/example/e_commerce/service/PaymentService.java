package com.example.e_commerce.service;

import com.example.e_commerce.Client.PaymentServiceClient;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.Payment;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentServiceClient paymentClient;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public Payment initiatePayment(PaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        request.setAmount(order.getTotalAmount()); // ensure amount matches order
        Payment payment = paymentClient.createPayment(request);

        paymentRepository.save(payment);
        return payment;
    }

    public void markPaymentSuccess(String paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        // Update order
        Order order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("PAID");
        orderRepository.save(order);
    }
}
