package com.example.healthcar.dto.car;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CarUpdateRequest {

  @NotBlank
  @Size(max = 50)
  private String maker;
  @NotBlank
  @Size(max = 100)
  private String carModel;
  private Integer modelYear;
  private String description;
  @PositiveOrZero
  private Integer odometer;
  @NotNull
  @Min(0)
  @Max(2)
  private Short status;

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }

  public Integer getModelYear() {
    return modelYear;
  }

  public void setModelYear(Integer modelYear) {
    this.modelYear = modelYear;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public void setOdometer(Integer odometer) {
    this.odometer = odometer;
  }

  public Short getStatus() {
    return status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }
}