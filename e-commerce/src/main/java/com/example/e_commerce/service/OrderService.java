package com.example.e_commerce.service;

import com.example.e_commerce.model.*;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.repository.OrderItemRepository;
import com.example.e_commerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    @Transactional
    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartRepository.findByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(user.getId());
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());

        orderRepository.save(order);

        double totalAmount = 0.0;

        for (CartItem ci : cartItems) {
            Product product = productService.getById(ci.getProductId());

            productService.validateStock(product, ci.getQuantity());
            productService.reduceStock(product, ci.getQuantity());

            OrderItem item = new OrderItem();
            item.setId(UUID.randomUUID().toString());
            item.setOrderId(order.getId());
            item.setProductId(product.getId());
            item.setQuantity(ci.getQuantity());
            item.setPrice(product.getPrice());

            orderItemRepository.save(item);

            totalAmount += product.getPrice() * ci.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        cartRepository.deleteByUserId(user.getId());

        return order;
    }

    public List<Order> getOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order findById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Transactional
    public void cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!"CREATED".equals(order.getStatus())) {
            throw new RuntimeException("Order cannot be cancelled");
        }

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        for (OrderItem item : items) {
            Product product = productService.getById(item.getProductId());
            product.setStock(product.getStock() + item.getQuantity());
            productService.save(product);
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}
