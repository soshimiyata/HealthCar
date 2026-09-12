package com.example.healthcar.service;

import com.example.healthcar.dto.custom.CustomCreateRequest;
import com.example.healthcar.dto.custom.CustomUpdateRequest;
import com.example.healthcar.dto.custom.CustomResponse;
import com.example.healthcar.dto.custom.CustomListResponse;
import com.example.healthcar.dto.custom.CustomDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.healthcar.entity.Custom;
import com.example.healthcar.repository.CarRepository;
import com.example.healthcar.repository.CustomRepository;
import com.example.healthcar.service.ImageService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class CustomService {

  private final CarRepository carRepository;
  private final CustomRepository customRepository;
  private final ImageService imageService;

  public CustomService(
      CarRepository carRepository,
      CustomRepository customRepository,
      ImageService imageService) {

    this.carRepository = carRepository;
    this.customRepository = customRepository;
    this.imageService = imageService;
  }

  public CustomResponse createCustom(
      Long carId,
      Long userId,
      CustomCreateRequest request,
      MultipartFile image) throws IOException {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    String imageUrl = imageService.saveImage(image, "customs");

    Custom custom = new Custom();
    custom.setCarId(carId);
    custom.setTitle(request.getTitle());
    custom.setDescription(request.getDescription());
    custom.setCustomDate(request.getCustomDate());
    custom.setCost(request.getCost());
    custom.setImageUrl(imageUrl);

    Custom savedCustom = customRepository.save(custom);

    return CustomResponse.from(savedCustom);
  }

  public Page<CustomListResponse> getCustoms(
      Long carId,
      Long userId,
      Pageable pageable) {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    return customRepository
        .findByCarId(carId, pageable)
        .map(CustomListResponse::from);
  }

  public CustomDetailResponse getCustom(
      Long customId,
      Long carId,
      Long userId) {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    Custom custom = customRepository
        .findByIdAndCarId(customId, carId)
        .orElseThrow(() -> new RuntimeException("Custom not found"));

    CustomDetailResponse detail = CustomDetailResponse.from(custom);

    List<CustomListResponse> recentCustoms = customRepository.findTop3ByCarIdOrderByCustomDateDesc(carId)
        .stream()
        .map(CustomListResponse::from)
        .toList();

    detail.setRecentCustoms(recentCustoms);

    return detail;
  }

  public CustomResponse updateCustom(
      Long customId,
      Long carId,
      Long userId,
      CustomUpdateRequest request,
      MultipartFile image) throws IOException {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    Custom custom = customRepository
        .findByIdAndCarId(customId, carId)
        .orElseThrow(() -> new RuntimeException("Custom not found"));

    custom.setTitle(request.getTitle());
    custom.setDescription(request.getDescription());
    custom.setCustomDate(request.getCustomDate());
    custom.setCost(request.getCost());

    if (image != null && !image.isEmpty()) {
      String imageUrl = imageService.saveImage(image, "customs");
      custom.setImageUrl(imageUrl);
    }

    Custom updatedCustom = customRepository.save(custom);

    return CustomResponse.from(updatedCustom);
  }
}