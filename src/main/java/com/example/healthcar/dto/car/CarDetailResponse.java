package com.example.healthcar.dto.car;

import com.example.healthcar.entity.Car;
import java.util.List;

import com.example.healthcar.dto.maintenance.MaintenanceListResponse;
import com.example.healthcar.dto.custom.CustomListResponse;
import com.example.healthcar.dto.part.PartListResponse;
import com.example.healthcar.dto.carissue.CarIssueListResponse;

public class CarDetailResponse {

  private Long id;
  private String maker;
  private String carModel;
  private Integer modelYear;
  private String description;
  private Integer odometer;
  private Short status;
  private String imageUrl;
  private CarSummaryResponse summary;
  private List<MaintenanceListResponse> recentMaintenances;
  private List<CustomListResponse> recentCustoms;
  private List<PartListResponse> currentParts;
  private List<CarIssueListResponse> recentCarIssues;

  public CarDetailResponse(
      Long id,
      String maker,
      String carModel,
      Integer modelYear,
      String description,
      Integer odometer,
      Short status,
      String imageUrl,
      CarSummaryResponse summary,
      List<MaintenanceListResponse> recentMaintenances,
      List<CustomListResponse> recentCustoms,
      List<PartListResponse> currentParts,
      List<CarIssueListResponse> recentCarIssues) {

    this.id = id;
    this.maker = maker;
    this.carModel = carModel;
    this.modelYear = modelYear;
    this.description = description;
    this.odometer = odometer;
    this.status = status;
    this.imageUrl = imageUrl;
    this.summary = summary;
    this.recentMaintenances = recentMaintenances;
    this.recentCustoms = recentCustoms;
    this.currentParts = currentParts;
    this.recentCarIssues = recentCarIssues;
  }

  public Long getId() {
    return id;
  }

  public String getMaker() {
    return maker;
  }

  public String getCarModel() {
    return carModel;
  }

  public Integer getModelYear() {
    return modelYear;
  }

  public String getDescription() {
    return description;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public Short getStatus() {
    return status;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public CarSummaryResponse getSummary() {
    return summary;
  }

  public List<MaintenanceListResponse> getRecentMaintenances() {
    return recentMaintenances;
  }

  public List<CustomListResponse> getRecentCustoms() {
    return recentCustoms;
  }

  public List<PartListResponse> getCurrentParts() {
    return currentParts;
  }

  public List<CarIssueListResponse> getRecentCarIssues() {
    return recentCarIssues;
  }
}