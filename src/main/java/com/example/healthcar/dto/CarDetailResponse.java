package com.example.healthcar.dto;

import com.example.healthcar.entity.Car;

public class CarDetailResponse {

  private Long id;
  private String maker;
  private String carModel;
  private Integer modelYear;
  private String description;
  private Integer odometer;
  private Short status;

  public CarDetailResponse(
      Long id,
      String maker,
      String carModel,
      Integer modelYear,
      String description,
      Integer odometer,
      Short status) {

    this.id = id;
    this.maker = maker;
    this.carModel = carModel;
    this.modelYear = modelYear;
    this.description = description;
    this.odometer = odometer;
    this.status = status;
  }

  public static CarDetailResponse from(Car car) {
    return new CarDetailResponse(
        car.getId(),
        car.getMaker(),
        car.getCarModel(),
        car.getModelYear(),
        car.getDescription(),
        car.getOdometer(),
        car.getStatus());
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
}