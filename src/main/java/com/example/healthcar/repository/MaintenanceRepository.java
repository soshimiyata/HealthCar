package com.example.healthcar.repository;

import com.example.healthcar.entity.Maintenance;
import com.example.healthcar.dto.MaintenanceDetailResponse;
import com.example.healthcar.entity.MaintenanceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MaintenanceRepository
    extends JpaRepository<Maintenance, Long> {
  Page<Maintenance> findByCarId(Long carId, Pageable pageable);

  @Query("""
          SELECT new com.example.healthcar.dto.MaintenanceDetailResponse(
              m.id,
              m.maintenanceType.id,
              mt.name,
              m.description,
              m.maintenanceDate,
              m.odometer,
              m.cost
          )
          FROM Maintenance m
          JOIN MaintenanceType mt
            ON m.maintenanceType.id = mt.id
          WHERE m.id = :maintenanceId
            AND m.carId = :carId
      """)
  Optional<MaintenanceDetailResponse> findDetail(
      @Param("maintenanceId") Long maintenanceId,
      @Param("carId") Long carId);
}