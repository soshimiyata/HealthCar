package com.example.healthcar.repository;

import com.example.healthcar.entity.Maintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository
    extends JpaRepository<Maintenance, Long> {
  Page<Maintenance> findByCarId(Long carId, Pageable pageable);
}