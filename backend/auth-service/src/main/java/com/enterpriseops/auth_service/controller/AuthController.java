package com.enterpriseops.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/api/auth/ping")
    public Map<String, String> ping() {
        return Map.of("message", "Auth Service is running");
    }
}
