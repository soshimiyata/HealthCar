package com.example.healthcar.dto;

import com.example.healthcar.entity.Custom;

import java.time.LocalDate;

public class CustomResponse {

  private Long id;
  private String title;
  private LocalDate customDate;

  public CustomResponse(
      Long id,
      String title,
      LocalDate customDate) {

    this.id = id;
    this.title = title;
    this.customDate = customDate;
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

  public static CustomResponse from(Custom custom) {
    return new CustomResponse(
        custom.getId(),
        custom.getTitle(),
        custom.getCustomDate());
  }
}