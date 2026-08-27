package com.example.healthcar.dto.car;

import com.example.healthcar.entity.Car;

public class CarResponse {

  private Long id;
  private String maker;
  private String carModel;
  private String imageUrl;

  public CarResponse(Long id, String maker, String carModel, String imageUrl) {
    this.id = id;
    this.maker = maker;
    this.carModel = carModel;
    this.imageUrl = imageUrl;
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

  public String getImageUrl() {
    return imageUrl;
  }
}