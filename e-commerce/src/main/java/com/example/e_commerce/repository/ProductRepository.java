package com.example.e_commerce.repository;

import com.example.e_commerce.model.OrderItem;
import com.example.e_commerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ $text : { $search:  ?0 } }")
    List<Product> search(String query);
}
