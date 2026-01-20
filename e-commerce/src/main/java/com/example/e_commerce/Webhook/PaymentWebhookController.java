package com.example.e_commerce.Webhook;

import com.example.e_commerce.dto.PaymentWebhookRequest;
import com.example.e_commerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhooks/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {
    private final PaymentService paymentService;

    @PostMapping
    public void handleWebhook(@RequestBody PaymentWebhookRequest request) {
        if ("SUCCESS".equalsIgnoreCase(request.getStatus())) {
            paymentService.markPaymentSuccess(request.getPaymentId());
        }
    }
}
