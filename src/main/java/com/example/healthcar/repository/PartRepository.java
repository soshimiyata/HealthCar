package com.example.healthcar.repository;

import com.example.healthcar.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.healthcar.dto.part.PartDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.healthcar.dto.summary.SummaryData;

import java.util.Optional;
import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {

  Optional<Part> findByIdAndCarId(Long id, Long carId);

  Page<Part> findByCarId(Long carId, Pageable pageable);

  @Query("""
      SELECT new com.example.healthcar.dto.part.PartDetailResponse(
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

  List<Part> findTop5ByCarIdAndStatusOrderByInstalledAtDesc(
      Long carId,
      Short status);

  @Query("""
      SELECT new com.example.healthcar.dto.summary.SummaryData(
          COUNT(p),
          COALESCE(SUM(p.price), 0)
      )
      FROM Part p
      WHERE p.carId = :carId
      """)
  SummaryData findSummary(@Param("carId") Long carId);
}