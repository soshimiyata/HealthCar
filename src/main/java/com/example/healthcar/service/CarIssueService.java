package com.example.healthcar.service;

import com.example.healthcar.dto.carissue.CarIssueCreateRequest;
import com.example.healthcar.dto.carissue.CarIssueCreateResponse;
import com.example.healthcar.dto.carissue.CarIssueListResponse;
import com.example.healthcar.dto.carissue.CarIssueDetailResponse;
import com.example.healthcar.dto.carissue.CarIssueUpdateRequest;
import com.example.healthcar.dto.carissue.CarIssueUpdateResponse;
import com.example.healthcar.entity.CarIssue;
import com.example.healthcar.repository.CarIssueRepository;
import com.example.healthcar.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarIssueService {

  private final CarIssueRepository carIssueRepository;
  private final CarRepository carRepository;

  public CarIssueService(
      CarIssueRepository carIssueRepository,
      CarRepository carRepository) {
    this.carIssueRepository = carIssueRepository;
    this.carRepository = carRepository;
  }

  public CarIssueCreateResponse createIssue(
      Long carId,
      Long userId,
      CarIssueCreateRequest request) {

    // 車両の所有者チェック
    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    // Issue作成
    CarIssue issue = new CarIssue();

    issue.setCarId(carId);
    issue.setTitle(request.getTitle());
    issue.setDescription(request.getDescription());
    issue.setStatus(request.getStatus() != null ? request.getStatus() : (short) 0);
    issue.setPriority(request.getPriority() != null ? request.getPriority() : (short) 1);
    issue.setImageUrl(request.getImageUrl());
    issue.setOccurredAt(request.getOccurredAt());
    issue.setResolvedAt(request.getResolvedAt());

    CarIssue savedIssue = carIssueRepository.save(issue);

    return new CarIssueCreateResponse(
        savedIssue.getId(),
        savedIssue.getTitle(),
        savedIssue.getStatus(),
        savedIssue.getPriority());
  }

  public Page<CarIssueListResponse> getIssues(
      Long carId,
      Long userId,
      Pageable pageable) {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    return carIssueRepository
        .findByCarId(carId, pageable)
        .map(CarIssueListResponse::from);
  }

  public CarIssueDetailResponse getIssue(
      Long carId,
      Long issueId,
      Long userId) {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    CarIssue issue = carIssueRepository
        .findByIdAndCarId(issueId, carId)
        .orElseThrow(() -> new RuntimeException("Issue not found"));

    return CarIssueDetailResponse.from(issue);
  }

  public CarIssueUpdateResponse updateIssue(
      Long carId,
      Long issueId,
      Long userId,
      CarIssueUpdateRequest request) {

    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    CarIssue issue = carIssueRepository
        .findByIdAndCarId(issueId, carId)
        .orElseThrow(() -> new RuntimeException("Issue not found"));

    issue.setTitle(request.getTitle());
    issue.setDescription(request.getDescription());
    issue.setStatus(request.getStatus());
    issue.setPriority(request.getPriority());
    issue.setImageUrl(request.getImageUrl());
    issue.setOccurredAt(request.getOccurredAt());
    issue.setResolvedAt(request.getResolvedAt());

    carIssueRepository.save(issue);

    return CarIssueUpdateResponse.from(issue);
  }
}