package com.example.healthcar.dto;

import java.time.LocalDate;

public class CustomCreateRequest {

  private String title;
  private String description;
  private LocalDate customDate;
  private Integer cost;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCustomDate() {
    return customDate;
  }

  public void setCustomDate(LocalDate customDate) {
    this.customDate = customDate;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }
}