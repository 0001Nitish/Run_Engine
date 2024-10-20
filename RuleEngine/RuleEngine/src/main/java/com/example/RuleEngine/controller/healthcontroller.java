package com.example.RuleEngine.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health/")
public class healthcontroller {
    @GetMapping
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Service is running...");
    }
}
