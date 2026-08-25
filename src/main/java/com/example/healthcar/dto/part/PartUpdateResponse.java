package com.example.healthcar.dto.part;

public class PartUpdateResponse {

  private Long id;
  private String name;

  public PartUpdateResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}