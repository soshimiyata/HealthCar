package com.example.healthcar.repository;

import com.example.healthcar.entity.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.healthcar.dto.summary.SummaryData;

import java.util.Optional;
import java.util.List;

public interface CustomRepository
    extends JpaRepository<Custom, Long> {

  Optional<Custom> findByIdAndCarId(Long id, Long carId);

  Page<Custom> findByCarId(Long carId, Pageable pageable);

  List<Custom> findTop3ByCarIdOrderByCustomDateDesc(Long carId);

  @Query("""
      SELECT new com.example.healthcar.dto.summary.SummaryData(
          COUNT(c),
          COALESCE(SUM(c.cost), 0)
      )
      FROM Custom c
      WHERE c.carId = :carId
      """)
  SummaryData findSummary(@Param("carId") Long carId);
}