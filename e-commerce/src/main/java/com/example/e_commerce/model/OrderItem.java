package com.example.e_commerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection="order_item")
public class OrderItem {
    @Id
    private String id;
    private String orderId;
    private String productId;
    private int quantity;
    private double price;
}
