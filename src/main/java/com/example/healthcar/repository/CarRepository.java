package com.example.healthcar.repository;

import com.example.healthcar.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

  Page<Car> findByUserId(Long userId, Pageable pageable);

  Optional<Car> findByIdAndUserId(Long id, Long userId);
}