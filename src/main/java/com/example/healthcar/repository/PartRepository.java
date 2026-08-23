package com.example.healthcar.repository;

import com.example.healthcar.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.healthcar.dto.PartDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {

  Optional<Part> findByIdAndCarId(Long id, Long carId);

  Page<Part> findByCarId(Long carId, Pageable pageable);

  @Query("""
      SELECT new com.example.healthcar.dto.PartDetailResponse(
          p.id,
          p.category,
          p.name,
          p.maker,
          p.installedAt,
          p.price,
          p.description,
          p.imageUrl,
          p.status
      )
      FROM Part p
      WHERE p.id = :partId
        AND p.carId = :carId
      """)
  Optional<PartDetailResponse> findDetail(
      @Param("partId") Long partId,
      @Param("carId") Long carId);
}