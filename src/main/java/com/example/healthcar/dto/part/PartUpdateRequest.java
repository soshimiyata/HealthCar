package com.example.healthcar.dto.part;

import java.time.LocalDate;

public class PartUpdateRequest {

  private Short category;
  private String name;
  private String maker;
  private LocalDate installedAt;
  private Integer price;
  private String description;
  private String imageUrl;
  private Short status;

  public Short getCategory() {
    return category;
  }

  public void setCategory(Short category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public LocalDate getInstalledAt() {
    return installedAt;
  }

  public void setInstalledAt(LocalDate installedAt) {
    this.installedAt = installedAt;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Short getStatus() {
    return status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }
}