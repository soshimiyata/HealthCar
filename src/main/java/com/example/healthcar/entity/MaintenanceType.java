package com.example.healthcar.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_types")
public class MaintenanceType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String name;

  @Column(nullable = false)
  private Short category;

  @Column(name = "recommended_distance_km")
  private Integer recommendedDistanceKm;

  @Column(name = "recommended_period_months")
  private Integer recommendedPeriodMonths;

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

  public String getName() {
    return name;
  }

  public Short getCategory() {
    return category;
  }

  public Integer getRecommendedDistanceKm() {
    return recommendedDistanceKm;
  }

  public Integer getRecommendedPeriodMonths() {
    return recommendedPeriodMonths;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}