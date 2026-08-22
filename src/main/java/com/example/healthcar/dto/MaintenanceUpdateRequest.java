package com.example.healthcar.dto;

import java.time.LocalDate;

public class MaintenanceUpdateRequest {

  private Long maintenanceTypeId;
  private String description;
  private LocalDate maintenanceDate;
  private Integer odometer;
  private Integer cost;

  public Long getMaintenanceTypeId() {
    return maintenanceTypeId;
  }

  public void setMaintenanceTypeId(Long maintenanceTypeId) {
    this.maintenanceTypeId = maintenanceTypeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getMaintenanceDate() {
    return maintenanceDate;
  }

  public void setMaintenanceDate(LocalDate maintenanceDate) {
    this.maintenanceDate = maintenanceDate;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public void setOdometer(Integer odometer) {
    this.odometer = odometer;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }
}