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
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    private String userId;
    private double totalAmount;
    private String status;
    private Instant createdAt;
}
