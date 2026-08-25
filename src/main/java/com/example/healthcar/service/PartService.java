package com.example.healthcar.service;

import com.example.healthcar.dto.part.PartCreateRequest;
import com.example.healthcar.dto.part.PartResponse;
import com.example.healthcar.dto.part.PartListResponse;
import com.example.healthcar.dto.part.PartDetailResponse;
import com.example.healthcar.dto.part.PartUpdateRequest;
import com.example.healthcar.dto.part.PartUpdateResponse;
import com.example.healthcar.entity.Car;
import com.example.healthcar.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.healthcar.repository.CarRepository;
import com.example.healthcar.repository.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartService {

  private final PartRepository partRepository;
  private final CarRepository carRepository;

  public PartService(
      PartRepository partRepository,
      CarRepository carRepository) {

    this.partRepository = partRepository;
    this.carRepository = carRepository;
  }

  public PartResponse createPart(
      Long carId,
      Long userId,
      PartCreateRequest request) {

    Car car = carRepository
        .findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    Part part = new Part();

    part.setCarId(car.getId());
    part.setCategory(request.getCategory());
    part.setName(request.getName());
    part.setMaker(request.getMaker());
    part.setInstalledAt(request.getInstalledAt());
    part.setPrice(
        request.getPrice() != null ? request.getPrice() : 0);
    part.setDescription(request.getDescription());
    part.setImageUrl(request.getImageUrl());
    part.setStatus(request.getStatus());

    Part savedPart = partRepository.save(part);

    return PartResponse.from(savedPart);
  }

  public Page<PartListResponse> findParts(Long carId, Pageable pageable) {

    return partRepository
        .findByCarId(carId, pageable)
        .map(PartListResponse::from);
  }

  public PartDetailResponse getPartDetail(Long carId, Long partId) {

    return partRepository
        .findDetail(partId, carId)
        .orElseThrow(() -> new RuntimeException("Part not found"));
  }

  public PartUpdateResponse updatePart(
      Long carId,
      Long partId,
      PartUpdateRequest request) {

    Part part = partRepository
        .findByIdAndCarId(partId, carId)
        .orElseThrow(() -> new RuntimeException("Part not found"));

    part.setCategory(request.getCategory());
    part.setName(request.getName());
    part.setMaker(request.getMaker());
    part.setInstalledAt(request.getInstalledAt());
    part.setPrice(request.getPrice());
    part.setDescription(request.getDescription());
    part.setImageUrl(request.getImageUrl());
    part.setStatus(request.getStatus());

    Part savedPart = partRepository.save(part);

    return new PartUpdateResponse(
        savedPart.getId(),
        savedPart.getName());
  }
}