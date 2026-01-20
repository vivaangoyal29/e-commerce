package com.example.e_commerce.controller;

import com.example.e_commerce.dto.AddToCartRequest;
import com.example.e_commerce.dto.CartItemResponse;
import com.example.e_commerce.model.CartItem;
import com.example.e_commerce.model.User;
import com.example.e_commerce.service.CartService;
import com.example.e_commerce.service.ProductService;
import com.example.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/add")
    public void addToCart(@RequestBody AddToCartRequest request) {
        User user = userService.getById(request.getUserId());
        cartService.addToCart(user, request);
    }

    @GetMapping
    public List<CartItemResponse> getCart(@RequestParam String userId) {
        List<CartItem> cartItems = cartService.getCart(userService.getById(userId).getId());

        return cartItems.stream().map(ci -> {
            var product = productService.getById(ci.getProductId());
            CartItemResponse dto = new CartItemResponse();
            dto.setProductId(product.getId());
            dto.setProductName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setQuantity(ci.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam String userId) {
        cartService.clearCart(userService.getById(userId).getId());
    }
}
