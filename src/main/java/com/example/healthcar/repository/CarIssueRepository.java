package com.example.healthcar.repository;

import java.util.Optional;
import java.util.List;

import com.example.healthcar.entity.CarIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarIssueRepository extends JpaRepository<CarIssue, Long> {

  Optional<CarIssue> findByIdAndCarId(Long id, Long carId);

  Page<CarIssue> findByCarId(Long carId, Pageable pageable);

  List<CarIssue> findTop3ByCarIdOrderByOccurredAtDesc(Long carId);
}