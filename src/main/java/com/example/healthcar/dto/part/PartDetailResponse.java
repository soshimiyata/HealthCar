package com.example.healthcar.dto.part;

import java.time.LocalDate;

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
}