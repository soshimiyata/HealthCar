package com.example.healthcar.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenances")
public class Maintenance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "car_id", nullable = false)
  private Long carId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "maintenance_type_id", nullable = false)
  private MaintenanceType maintenanceType;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "maintenance_date", nullable = false)
  private LocalDate maintenanceDate;

  private Integer odometer;

  @Column(nullable = false)
  private Integer cost = 0;

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

  public MaintenanceType getMaintenanceType() {
    return maintenanceType;
  }

  public void setMaintenanceType(MaintenanceType maintenanceType) {
    this.maintenanceType = maintenanceType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getMaintenanceDate() {
    return maintenanceDate;
  }

  public void setMaintenanceDate(LocalDate maintenanceDate) {
    this.maintenanceDate = maintenanceDate;
  }

  public Integer getOdometer() {
    return odometer;
  }

  public void setOdometer(Integer odometer) {
    this.odometer = odometer;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}