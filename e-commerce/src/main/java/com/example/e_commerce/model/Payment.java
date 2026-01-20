package com.example.e_commerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "payment")
public class Payment {
    @Id
    private String id;
    private String orderId;
    private double amount;
    private String status;
    private String paymentId;
    private Instant createdAt;
}
