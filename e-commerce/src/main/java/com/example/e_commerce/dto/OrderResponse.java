package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    private double totalAmount;
    private String status;
    private Instant createdAt;
}
