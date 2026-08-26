package com.example.healthcar.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class MaintenanceCreateRequest {

  @NotNull
  @Positive
  private Long maintenanceTypeId;
  private String description;
  @NotNull
  private LocalDate maintenanceDate;
  @PositiveOrZero
  private Integer odometer;
  @PositiveOrZero
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