package com.example.healthcar.dto.car;

public class CarSummaryResponse {

  private Integer maintenanceCount;
  private Integer customCount;
  private Integer partsCount;
  private Integer maintenanceCost;
  private Integer customCost;
  private Integer partsCost;
  private Integer totalCost;

  public CarSummaryResponse(
      Integer maintenanceCount,
      Integer customCount,
      Integer partsCount,
      Integer maintenanceCost,
      Integer customCost,
      Integer partsCost,
      Integer totalCost) {

    this.maintenanceCount = maintenanceCount;
    this.customCount = customCount;
    this.partsCount = partsCount;
    this.maintenanceCost = maintenanceCost;
    this.customCost = customCost;
    this.partsCost = partsCost;
    this.totalCost = totalCost;
  }

  public Integer getMaintenanceCount() {
    return maintenanceCount;
  }

  public Integer getCustomCount() {
    return customCount;
  }

  public Integer getPartsCount() {
    return partsCount;
  }

  public Integer getMaintenanceCost() {
    return maintenanceCost;
  }

  public Integer getCustomCost() {
    return customCost;
  }

  public Integer getPartsCost() {
    return partsCost;
  }

  public Integer getTotalCost() {
    return totalCost;
  }
}