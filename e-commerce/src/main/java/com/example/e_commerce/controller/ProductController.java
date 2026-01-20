package com.example.e_commerce.controller;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("q") String query) {
        return productService.search(query);
    }

}
