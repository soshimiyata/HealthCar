package com.example.healthcar.dto.custom;

import com.example.healthcar.entity.Custom;
import com.example.healthcar.dto.custom.CustomListResponse;

import java.time.LocalDate;
import java.util.List;

public class CustomDetailResponse {

  private Long id;
  private String title;
  private String description;
  private LocalDate customDate;
  private Integer cost;
  private String imageUrl;
  private List<CustomListResponse> recentCustoms;

  public CustomDetailResponse(
      Long id,
      String title,
      String description,
      LocalDate customDate,
      Integer cost,
      String imageUrl) {

    this.id = id;
    this.title = title;
    this.description = description;
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

  public String getDescription() {
    return description;
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

  public List<CustomListResponse> getRecentCustoms() {
    return recentCustoms;
  }

  public void setRecentCustoms(List<CustomListResponse> recentCustoms) {
    this.recentCustoms = recentCustoms;
  }

  public static CustomDetailResponse from(Custom custom) {
    return new CustomDetailResponse(
        custom.getId(),
        custom.getTitle(),
        custom.getDescription(),
        custom.getCustomDate(),
        custom.getCost(),
        custom.getImageUrl());
  }
}