package com.example.healthcar.controller;

import com.example.healthcar.dto.CarCreateRequest;
import com.example.healthcar.dto.CarUpdateRequest;
import com.example.healthcar.dto.CarResponse;
import com.example.healthcar.dto.CarDetailResponse;
import com.example.healthcar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping
  public ResponseEntity<CarResponse> createCar(
      @Valid @RequestBody CarCreateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    CarResponse response = carService.createCar(userId, request);

    return ResponseEntity
        .status(201)
        .body(response);
  }

  @GetMapping
  public ResponseEntity<Page<CarResponse>> getCars(
      Pageable pageable,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    Page<CarResponse> response = carService.getCars(userId, pageable);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{carId}")
  public ResponseEntity<CarDetailResponse> getCar(
      @PathVariable Long carId,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());
    CarDetailResponse response = carService.getCar(carId, userId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{carId}")
  public ResponseEntity<CarResponse> updateCar(
      @PathVariable Long carId,
      @RequestBody CarUpdateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    CarResponse response = carService.updateCar(carId, userId, request);

    return ResponseEntity.ok(response);
  }
}