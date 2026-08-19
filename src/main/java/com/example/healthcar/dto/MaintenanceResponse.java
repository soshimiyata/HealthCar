package com.example.healthcar.dto;

import com.example.healthcar.entity.Maintenance;

public class MaintenanceResponse {

  private Long id;
  private Long maintenanceTypeId;

  public MaintenanceResponse(Long id, Long maintenanceTypeId) {
    this.id = id;
    this.maintenanceTypeId = maintenanceTypeId;
  }

  public Long getId() {
    return id;
  }

  public Long getMaintenanceTypeId() {
    return maintenanceTypeId;
  }

  public static MaintenanceResponse from(Maintenance maintenance) {
    return new MaintenanceResponse(
        maintenance.getId(),
        maintenance.getMaintenanceType().getId());
  }
}