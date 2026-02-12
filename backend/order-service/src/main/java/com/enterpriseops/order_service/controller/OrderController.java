package com.enterpriseops.order_service.controller;

import com.enterpriseops.order_service.model.Order;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
@CrossOrigin(origins = "http://localhost:4200")


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private List<Order> orders = new ArrayList<>();

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("message", "Order Service is running");
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        orders.add(order);
        return order;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orders;
    }
}
