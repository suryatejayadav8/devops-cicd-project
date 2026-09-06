package com.devops.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {

        Map<String, String> response = new LinkedHashMap<>();

        response.put("message", "Welcome to DevOps CI/CD Project");
        response.put("version", "1.0");
        response.put("status", "Running Successfully");

        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {

        Map<String, String> response = new LinkedHashMap<>();

        response.put("status", "UP");

        return response;
    }
}