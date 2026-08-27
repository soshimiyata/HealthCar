package com.example.healthcar.dto.maintenance;

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
  private String imageUrl;

  public MaintenanceDetailResponse(
      Long id,
      Long maintenanceTypeId,
      String maintenanceTypeName,
      String description,
      LocalDate maintenanceDate,
      Integer odometer,
      Integer cost,
      String imageUrl) {

    this.id = id;
    this.maintenanceTypeId = maintenanceTypeId;
    this.maintenanceTypeName = maintenanceTypeName;
    this.description = description;
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

  public String getImageUrl() {
    return imageUrl;
  }
}