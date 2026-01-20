package com.example.e_commerce.controller;

import com.example.e_commerce.dto.OrderResponse;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.service.OrderService;
import com.example.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public OrderResponse createOrder(@RequestParam String userId) {
        Order order = orderService.placeOrder(userService.getById(userId));
        return toResponse(order);
    }

    @GetMapping
    public List<OrderResponse> getOrders(@RequestParam String userId) {
        return orderService.getOrders(userService.getById(userId).getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable String orderId) {
        Order order = orderService.findById(orderId);

        if (order == null) throw new RuntimeException("Order not found");

        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setOrderId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        return dto;
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersForUser(@PathVariable String userId) {
        return orderService.getOrdersForUser(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);
    }
}
