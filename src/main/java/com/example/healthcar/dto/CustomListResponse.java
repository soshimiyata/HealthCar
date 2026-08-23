package com.example.healthcar.dto;

import com.example.healthcar.entity.Custom;

import java.time.LocalDate;

public class CustomListResponse {

  private Long id;
  private String title;
  private LocalDate customDate;
  private Integer cost;

  public CustomListResponse(
      Long id,
      String title,
      LocalDate customDate,
      Integer cost) {

    this.id = id;
    this.title = title;
    this.customDate = customDate;
    this.cost = cost;
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

  public static CustomListResponse from(Custom custom) {
    return new CustomListResponse(
        custom.getId(),
        custom.getTitle(),
        custom.getCustomDate(),
        custom.getCost());
  }
}