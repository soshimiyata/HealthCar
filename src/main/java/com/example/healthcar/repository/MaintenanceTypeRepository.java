package com.example.healthcar.repository;

import com.example.healthcar.entity.MaintenanceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTypeRepository
    extends JpaRepository<MaintenanceType, Long> {
}