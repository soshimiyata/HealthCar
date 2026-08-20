package com.example.healthcar.dto;

import com.example.healthcar.entity.Maintenance;

import java.time.LocalDate;

public class MaintenanceDetailResponse {

  private Long id;
  private Long maintenanceTypeId;
  private String maintenanceTypeName;
  private String description;
  private LocalDate maintenanceDate;
  private Integer odometer;
  private Integer cost;

  public MaintenanceDetailResponse(
      Long id,
      Long maintenanceTypeId,
      String maintenanceTypeName,
      String description,
      LocalDate maintenanceDate,
      Integer odometer,
      Integer cost) {

    this.id = id;
    this.maintenanceTypeId = maintenanceTypeId;
    this.maintenanceTypeName = maintenanceTypeName;
    this.description = description;
    this.maintenanceDate = maintenanceDate;
    this.odometer = odometer;
    this.cost = cost;
  }

  public Long getId() {
    return id;
  }

  public Long getMaintenanceTypeId() {
    return maintenanceTypeId;
  }

  public String getMaintenanceTypeName() {
    return maintenanceTypeName;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getMaintenanceDate() {
    return maintenanceDate;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public Integer getCost() {
    return cost;
  }
}