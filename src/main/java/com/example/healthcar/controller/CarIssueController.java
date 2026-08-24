package com.example.healthcar.controller;

import com.example.healthcar.dto.CarIssueCreateRequest;
import com.example.healthcar.dto.CarIssueCreateResponse;
import com.example.healthcar.dto.CarIssueListResponse;
import com.example.healthcar.service.CarIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cars/{carId}/issues")
public class CarIssueController {

  private final CarIssueService carIssueService;

  public CarIssueController(CarIssueService carIssueService) {
    this.carIssueService = carIssueService;
  }

  @PostMapping
  public ResponseEntity<CarIssueCreateResponse> createIssue(
      @PathVariable Long carId,
      @RequestBody CarIssueCreateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    CarIssueCreateResponse response = carIssueService.createIssue(carId, userId, request);

    return ResponseEntity
        .created(URI.create(
            "/api/cars/" + carId + "/issues/" + response.getId()))
        .body(response);
  }

  @GetMapping
  public ResponseEntity<Page<CarIssueListResponse>> getIssues(
      @PathVariable Long carId,
      Pageable pageable,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    Page<CarIssueListResponse> response = carIssueService.getIssues(carId, userId, pageable);

    return ResponseEntity.ok(response);
  }
}