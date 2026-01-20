package com.example.e_commerce.controller;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.model.Payment;
import com.example.e_commerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.initiatePayment(request);
    }

    @PostMapping("/mock/success")
    public void mockPaymentSuccess(@RequestParam String paymentId) {
        paymentService.markPaymentSuccess(paymentId);
    }
}
