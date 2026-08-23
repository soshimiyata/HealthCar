package com.example.healthcar.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "parts")
public class Part {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "car_id", nullable = false)
  private Long carId;

  @Column(nullable = false)
  private Short category;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 100)
  private String maker;

  @Column(name = "installed_at")
  private LocalDate installedAt;

  @Column(nullable = false)
  private Integer price = 0;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "image_url", columnDefinition = "TEXT")
  private String imageUrl;

  @Column(nullable = false)
  private Short status = 0;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    createdAt = now;
    updatedAt = now;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}