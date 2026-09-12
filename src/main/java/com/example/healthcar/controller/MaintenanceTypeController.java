package com.example.healthcar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcar.dto.maintenance.MaintenanceTypeListBoxResponse;
import com.example.healthcar.service.MaintenanceTypeService;

@RestController
@RequestMapping("/api/maintenance-types")
public class MaintenanceTypeController {

  private final MaintenanceTypeService maintenanceTypeService;

  public MaintenanceTypeController(
      MaintenanceTypeService maintenanceTypeService) {
    this.maintenanceTypeService = maintenanceTypeService;
  }

  @GetMapping
  public ResponseEntity<List<MaintenanceTypeListBoxResponse>> getMaintenanceTypes() {

    List<MaintenanceTypeListBoxResponse> response = maintenanceTypeService.getMaintenanceTypes();

    return ResponseEntity.ok(response);
  }
}