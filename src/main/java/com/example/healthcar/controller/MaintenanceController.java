package com.example.healthcar.controller;

import com.example.healthcar.dto.MaintenanceCreateRequest;
import com.example.healthcar.dto.MaintenanceUpdateRequest;
import com.example.healthcar.dto.MaintenanceResponse;
import com.example.healthcar.dto.MaintenanceListResponse;
import com.example.healthcar.dto.MaintenanceDetailResponse;
import com.example.healthcar.service.MaintenanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cars/{carId}/maintenances")
public class MaintenanceController {

  private final MaintenanceService maintenanceService;

  public MaintenanceController(
      MaintenanceService maintenanceService) {

    this.maintenanceService = maintenanceService;
  }

  @PostMapping
  public ResponseEntity<MaintenanceResponse> createMaintenance(
      @PathVariable Long carId,
      @RequestBody MaintenanceCreateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    MaintenanceResponse response = maintenanceService.createMaintenance(
        carId,
        userId,
        request);

    return ResponseEntity
        .created(URI.create(
            "/api/cars/" + carId +
                "/maintenances/" + response.getId()))
        .body(response);
  }

  @GetMapping
  public Page<MaintenanceListResponse> getMaintenances(
      @PathVariable Long carId,
      Pageable pageable,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    return maintenanceService.getMaintenances(
        carId,
        userId,
        pageable);
  }

  @GetMapping("/{maintenanceId}")
  public ResponseEntity<MaintenanceDetailResponse> getMaintenance(
      @PathVariable Long carId,
      @PathVariable Long maintenanceId,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    MaintenanceDetailResponse response = maintenanceService.getMaintenance(
        carId,
        maintenanceId,
        userId);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{maintenanceId}")
  public ResponseEntity<MaintenanceDetailResponse> updateMaintenance(
      @PathVariable Long carId,
      @PathVariable Long maintenanceId,
      @RequestBody MaintenanceUpdateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    MaintenanceDetailResponse response = maintenanceService.updateMaintenance(
        carId,
        maintenanceId,
        request,
        userId);

    return ResponseEntity.ok(response);
  }
}