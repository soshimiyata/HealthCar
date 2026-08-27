package com.example.healthcar.dto.maintenance;

import com.example.healthcar.entity.Maintenance;

import java.time.LocalDate;

public class MaintenanceListResponse {

  private Long id;
  private Long maintenanceTypeId;
  private String maintenanceTypeName;
  private LocalDate maintenanceDate;
  private Integer odometer;
  private Integer cost;
  private String imageUrl;

  public MaintenanceListResponse(
      Long id,
      Long maintenanceTypeId,
      String maintenanceTypeName,
      LocalDate maintenanceDate,
      Integer odometer,
      Integer cost,
      String imageUrl) {

    this.id = id;
    this.maintenanceTypeId = maintenanceTypeId;
    this.maintenanceTypeName = maintenanceTypeName;
    this.maintenanceDate = maintenanceDate;
    this.odometer = odometer;
    this.cost = cost;
    this.imageUrl = imageUrl;
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

  public LocalDate getMaintenanceDate() {
    return maintenanceDate;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public Integer getCost() {
    return cost;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public static MaintenanceListResponse from(Maintenance maintenance) {
    return new MaintenanceListResponse(
        maintenance.getId(),
        maintenance.getMaintenanceType().getId(),
        maintenance.getMaintenanceType().getName(),
        maintenance.getMaintenanceDate(),
        maintenance.getOdometer(),
        maintenance.getCost(),
        maintenance.getMaintenanceType().getImageUrl());
  }
}