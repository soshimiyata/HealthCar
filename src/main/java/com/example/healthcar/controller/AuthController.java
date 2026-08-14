package com.example.healthcar.controller;

import jakarta.validation.Valid;

import com.example.healthcar.dto.auth.LoginRequest;
import com.example.healthcar.dto.auth.LoginResponse;
import com.example.healthcar.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
      @Valid @RequestBody LoginRequest request) {

    LoginResponse response = authService.login(request);

    return ResponseEntity.ok(response);
  }
}