package com.example.e_commerce.service;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getById(String productId){
        return productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found"));
    }
    public void validateStock(Product product, int quantity){
        if(product.getStock() < quantity){
            throw new RuntimeException("Insufficient stock");
        }
    }
    public void reduceStock(Product product, int quantity){
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public List<Product> search(String query){
        return productRepository.search(query);
    }
}
