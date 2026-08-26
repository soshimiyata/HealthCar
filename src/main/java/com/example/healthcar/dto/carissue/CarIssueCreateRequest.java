package com.example.healthcar.dto.carissue;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CarIssueCreateRequest {

  @NotBlank
  @Size(max = 100)
  private String title;
  private String description;
  @Min(0)
  @Max(2)
  private Short status;
  @Min(0)
  @Max(2)
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
  @AssertTrue
  public boolean isResolvedAtValid() {
    short effectiveStatus = status == null ? 0 : status;
    return effectiveStatus == 2 ? resolvedAt != null : resolvedAt == null;
  }
}
