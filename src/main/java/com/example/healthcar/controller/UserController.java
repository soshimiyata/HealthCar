package com.example.healthcar.controller;

import com.example.healthcar.dto.UserResponse;
import com.example.healthcar.dto.UserCreateRequest;
import com.example.healthcar.dto.UserUpdateRequest;
import com.example.healthcar.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(
      @Valid @RequestBody UserCreateRequest request) {
    UserResponse response = userService.createUser(request);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
    UserResponse response = userService.getCurrentUser(authentication);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/me")
  public ResponseEntity<UserResponse> updateMe(
      @Valid @RequestBody UserUpdateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    UserResponse response = userService.updateUser(userId, request);

    return ResponseEntity.ok(response);
  }
}