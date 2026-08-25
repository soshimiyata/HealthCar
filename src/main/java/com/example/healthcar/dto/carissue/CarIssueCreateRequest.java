package com.example.healthcar.dto.carissue;

import java.time.LocalDate;

public class CarIssueCreateRequest {

  private String title;
  private String description;
  private Short status;
  private Short priority;
  private String imageUrl;
  private LocalDate occurredAt;
  private LocalDate resolvedAt;

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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
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
}