package com.example.healthcar.dto.carissue;

import com.example.healthcar.entity.CarIssue;

import java.time.LocalDate;

public class CarIssueListResponse {

  private Long id;
  private String title;
  private Short status;
  private Short priority;
  private LocalDate occurredAt;

  public CarIssueListResponse(
      Long id,
      String title,
      Short status,
      Short priority,
      LocalDate occurredAt) {

    this.id = id;
    this.title = title;
    this.status = status;
    this.priority = priority;
    this.occurredAt = occurredAt;
  }

  public static CarIssueListResponse from(CarIssue carIssue) {
    return new CarIssueListResponse(
        carIssue.getId(),
        carIssue.getTitle(),
        carIssue.getStatus(),
        carIssue.getPriority(),
        carIssue.getOccurredAt());
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Short getStatus() {
    return status;
  }

  public Short getPriority() {
    return priority;
  }

  public LocalDate getOccurredAt() {
    return occurredAt;
  }
}