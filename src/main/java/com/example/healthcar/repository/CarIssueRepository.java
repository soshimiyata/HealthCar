package com.example.healthcar.repository;

import com.example.healthcar.entity.CarIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarIssueRepository extends JpaRepository<CarIssue, Long> {

  Optional<CarIssue> findByIdAndCarId(Long id, Long carId);

  Page<CarIssue> findByCarId(Long carId, Pageable pageable);
}