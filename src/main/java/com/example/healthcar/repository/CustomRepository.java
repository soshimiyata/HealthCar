package com.example.healthcar.repository;

import com.example.healthcar.entity.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomRepository
    extends JpaRepository<Custom, Long> {

  Optional<Custom> findByIdAndCarId(Long id, Long carId);

  Page<Custom> findByCarId(Long carId, Pageable pageable);
}