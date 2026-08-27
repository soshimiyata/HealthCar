package com.example.healthcar.dto.custom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CustomCreateRequest {

  @NotBlank
  @Size(max = 100)
  private String title;
  private String description;
  @NotNull
  private LocalDate customDate;
  @PositiveOrZero
  private Integer cost;
  private String imageUrl;

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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}