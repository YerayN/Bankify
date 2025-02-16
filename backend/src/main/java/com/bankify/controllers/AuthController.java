package com.bankify.controllers;

import com.bankify.dto.LoginRequest;
import com.bankify.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
