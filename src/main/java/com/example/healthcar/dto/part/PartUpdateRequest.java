package com.example.healthcar.dto.part;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

public class PartUpdateRequest {

  @NotNull
  @Min(0)
  @Max(9)
  private Short category;
  @NotBlank
  @Size(max = 100)
  private String name;
  @Size(max = 100)
  private String maker;
  private LocalDate installedAt;
  @PositiveOrZero
  private Integer price;
  private String description;
  @URL
  private String imageUrl;
  @NotNull
  @Min(0)
  @Max(4)
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