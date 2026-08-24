package com.example.healthcar.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_issues")
public class CarIssue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "car_id", nullable = false)
  private Long carId;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private Short status = 0;

  @Column(nullable = false)
  private Short priority = 1;

  @Column(name = "occurred_at")
  private LocalDate occurredAt;

  @Column(name = "resolved_at")
  private LocalDate resolvedAt;

  @Column(name = "image_url", columnDefinition = "TEXT")
  private String imageUrl;

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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

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

  public Short getStatus() {
    return status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }

  public Short getPriority() {
    return priority;
  }

  public void setPriority(Short priority) {
    this.priority = priority;
  }

  public LocalDate getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(LocalDate occurredAt) {
    this.occurredAt = occurredAt;
  }

  public LocalDate getResolvedAt() {
    return resolvedAt;
  }

  public void setResolvedAt(LocalDate resolvedAt) {
    this.resolvedAt = resolvedAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}