package com.example.healthcar.dto.custom;

import com.example.healthcar.entity.Custom;

import java.time.LocalDate;

public class CustomListResponse {

  private Long id;
  private String title;
  private LocalDate customDate;
  private Integer cost;
  private String imageUrl;

  public CustomListResponse(
      Long id,
      String title,
      LocalDate customDate,
      Integer cost,
      String imageUrl) {

    this.id = id;
    this.title = title;
    this.customDate = customDate;
    this.cost = cost;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getCustomDate() {
    return customDate;
  }

  public Integer getCost() {
    return cost;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public static CustomListResponse from(Custom custom) {
    return new CustomListResponse(
        custom.getId(),
        custom.getTitle(),
        custom.getCustomDate(),
        custom.getCost(),
        custom.getImageUrl());
  }
}