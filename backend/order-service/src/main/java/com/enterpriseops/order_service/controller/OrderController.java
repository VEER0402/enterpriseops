package com.enterpriseops.order_service.controller;

import com.enterpriseops.order_service.model.Order;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/auth-check")
public ResponseEntity<String> callAuthService() {
    String authUrl = System.getenv("AUTH_SERVICE_URL");

    try {
        java.net.URL url = new java.net.URL(authUrl + "/api/auth/ping");
        java.net.HttpURLConnection connection =
                (java.net.HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(2000); // 2 sec connect timeout
        connection.setReadTimeout(2000);    // 2 sec read timeout
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();

        if (status != 200) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Auth service unavailable");
        }

        java.io.BufferedReader in =
                new java.io.BufferedReader(
                        new java.io.InputStreamReader(connection.getInputStream())
                );

        String response = in.readLine();
        in.close();

        return ResponseEntity.ok("Response from Auth-Service: " + response);

    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Auth service unreachable: " + e.getMessage());
    }
}


}
