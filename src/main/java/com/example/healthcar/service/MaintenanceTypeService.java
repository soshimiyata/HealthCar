package com.example.healthcar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.healthcar.dto.maintenance.MaintenanceTypeListBoxResponse;
import com.example.healthcar.repository.MaintenanceTypeRepository;

@Service
public class MaintenanceTypeService {

  private final MaintenanceTypeRepository maintenanceTypeRepository;

  public MaintenanceTypeService(
      MaintenanceTypeRepository maintenanceTypeRepository) {
    this.maintenanceTypeRepository = maintenanceTypeRepository;
  }

  public List<MaintenanceTypeListBoxResponse> getMaintenanceTypes() {

    return maintenanceTypeRepository.findAll()
        .stream()
        .map(type -> new MaintenanceTypeListBoxResponse(
            type.getId(),
            type.getName()))
        .toList();
  }
}