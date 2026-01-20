package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
}
