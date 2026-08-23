package com.example.healthcar.dto;

import com.example.healthcar.entity.Custom;

import java.time.LocalDate;

public class CustomDetailResponse {

  private Long id;
  private String title;
  private String description;
  private LocalDate customDate;
  private Integer cost;

  public CustomDetailResponse(
      Long id,
      String title,
      String description,
      LocalDate customDate,
      Integer cost) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.customDate = customDate;
    this.cost = cost;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getCustomDate() {
    return customDate;
  }

  public Integer getCost() {
    return cost;
  }

  public static CustomDetailResponse from(Custom custom) {
    return new CustomDetailResponse(
        custom.getId(),
        custom.getTitle(),
        custom.getDescription(),
        custom.getCustomDate(),
        custom.getCost());
  }
}