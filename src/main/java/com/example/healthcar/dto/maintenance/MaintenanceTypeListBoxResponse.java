package com.example.healthcar.dto.maintenance;

public class MaintenanceTypeListBoxResponse {

  private Long typeId;

  private String typeName;

  public MaintenanceTypeListBoxResponse(
      Long typeId,
      String typeName) {
    this.typeId = typeId;
    this.typeName = typeName;
  }

  public Long getTypeId() {
    return typeId;
  }

  public String getTypeName() {
    return typeName;
  }
}