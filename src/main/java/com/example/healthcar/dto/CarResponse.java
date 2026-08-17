package com.example.healthcar.dto;

import com.example.healthcar.entity.Car;

public class CarResponse {

  private Long id;
  private String maker;
  private String carModel;

  public CarResponse(Long id, String maker, String carModel) {
    this.id = id;
    this.maker = maker;
    this.carModel = carModel;
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
}