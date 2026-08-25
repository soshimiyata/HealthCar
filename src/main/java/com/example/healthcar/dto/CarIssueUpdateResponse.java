package com.example.healthcar.dto;

import com.example.healthcar.entity.CarIssue;

public class CarIssueUpdateResponse {

  private Long id;
  private String title;
  private Short status;
  private Short priority;

  public CarIssueUpdateResponse(
      Long id,
      String title,
      Short status,
      Short priority) {

    this.id = id;
    this.title = title;
    this.status = status;
    this.priority = priority;
  }

  public static CarIssueUpdateResponse from(CarIssue issue) {
    return new CarIssueUpdateResponse(
        issue.getId(),
        issue.getTitle(),
        issue.getStatus(),
        issue.getPriority());
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
}