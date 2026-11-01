package com.JwtDemo.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.JwtDemo.DTO.AuthResponse;
import com.JwtDemo.DTO.LoginRequest;
import com.JwtDemo.DTO.RegisterRequest;
import com.JwtDemo.Services.AuthService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/saveRegister")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/CheckLogin")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
