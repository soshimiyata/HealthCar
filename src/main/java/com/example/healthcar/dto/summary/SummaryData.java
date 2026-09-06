package com.example.healthcar.dto.summary;

public class SummaryData {

  private Long count;
  private Long cost;

  public SummaryData(Long count, Long cost) {
    this.count = count;
    this.cost = cost;
  }

  public Long getCount() {
    return count;
  }

  public Long getCost() {
    return cost;
  }
}