package com.example.healthcar.dto;

import com.example.healthcar.entity.Part;

public class PartResponse {

  private Long id;
  private String name;

  public PartResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static PartResponse from(Part part) {
    return new PartResponse(
        part.getId(),
        part.getName());
  }
}