package com.example.e_commerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @TextIndexed
    private String name;
    private double price;
    private int stock;
}
