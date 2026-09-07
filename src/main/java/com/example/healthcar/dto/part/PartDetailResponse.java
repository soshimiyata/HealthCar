package com.example.healthcar.dto.part;

import com.example.healthcar.dto.part.PartListResponse;

import java.time.LocalDate;
import java.util.List;

public class PartDetailResponse {

  private Long id;
  private Short category;
  private String name;
  private String maker;
  private LocalDate installedAt;
  private Integer price;
  private String description;
  private String imageUrl;
  private Short status;
  private List<PartListResponse> currentParts;

  public PartDetailResponse(
      Long id,
      Short category,
      String name,
      String maker,
      LocalDate installedAt,
      Integer price,
      String description,
      String imageUrl,
      Short status) {

    this.id = id;
    this.category = category;
    this.name = name;
    this.maker = maker;
    this.installedAt = installedAt;
    this.price = price;
    this.description = description;
    this.imageUrl = imageUrl;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public Short getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public String getMaker() {
    return maker;
  }

  public LocalDate getInstalledAt() {
    return installedAt;
  }

  public Integer getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Short getStatus() {
    return status;
  }

  public List<PartListResponse> getCurrentParts() {
    return currentParts;
  }

  public void setCurrentParts(List<PartListResponse> currentParts) {
    this.currentParts = currentParts;
  }
}