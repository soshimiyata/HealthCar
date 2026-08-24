package com.example.healthcar.dto;

public class CarIssueCreateResponse {

  private Long id;
  private String title;
  private Short status;
  private Short priority;

  public CarIssueCreateResponse(
      Long id,
      String title,
      Short status,
      Short priority) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.priority = priority;
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