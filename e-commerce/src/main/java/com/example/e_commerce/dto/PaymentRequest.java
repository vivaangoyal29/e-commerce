package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String orderId;
    private double amount;
}

