package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private double amount;
    private String status;
    private Instant createdAt;
}
