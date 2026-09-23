package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ApiResponse;
import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    // ========================================
    // REGISTER
    // ========================================

    @PostMapping("/register")
    public ResponseEntity<ApiResponse>
    register(
            @Valid @RequestBody User user) {

        String message =
                authService.register(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse(message)
                );
    }

    // ========================================
    // LOGIN
    // ========================================

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>>
    login(
            @Valid @RequestBody
            LoginRequest request) {

        String token =
                authService.login(
                        request.getEmail(),
                        request.getPassword()
                );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Login successful",
                        "token",
                        token
                )
        );
    }
}