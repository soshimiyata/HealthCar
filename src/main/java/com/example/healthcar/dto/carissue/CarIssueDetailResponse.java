package com.example.healthcar.dto.carissue;

import com.example.healthcar.entity.CarIssue;

import java.time.LocalDate;

public class CarIssueDetailResponse {

  private Long id;
  private String title;
  private String description;
  private Short status;
  private Short priority;
  private String imageUrl;
  private LocalDate occurredAt;
  private LocalDate resolvedAt;

  public CarIssueDetailResponse(
      Long id,
      String title,
      String description,
      Short status,
      Short priority,
      String imageUrl,
      LocalDate occurredAt,
      LocalDate resolvedAt) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.imageUrl = imageUrl;
    this.occurredAt = occurredAt;
    this.resolvedAt = resolvedAt;
  }

  public static CarIssueDetailResponse from(CarIssue issue) {
    return new CarIssueDetailResponse(
        issue.getId(),
        issue.getTitle(),
        issue.getDescription(),
        issue.getStatus(),
        issue.getPriority(),
        issue.getImageUrl(),
        issue.getOccurredAt(),
        issue.getResolvedAt());
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Short getStatus() {
    return status;
  }

  public Short getPriority() {
    return priority;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public LocalDate getOccurredAt() {
    return occurredAt;
  }

  public LocalDate getResolvedAt() {
    return resolvedAt;
  }
}