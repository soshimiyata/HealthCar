package com.example.healthcar.controller;

import com.example.healthcar.dto.carissue.CarIssueCreateRequest;
import com.example.healthcar.dto.carissue.CarIssueCreateResponse;
import com.example.healthcar.dto.carissue.CarIssueDetailResponse;
import com.example.healthcar.dto.carissue.CarIssueUpdateRequest;
import com.example.healthcar.dto.carissue.CarIssueUpdateResponse;
import com.example.healthcar.dto.carissue.CarIssueListResponse;
import com.example.healthcar.service.CarIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.net.URI;

@RestController
@RequestMapping("/api/cars/{carId}/issues")
public class CarIssueController {

  private final CarIssueService carIssueService;

  public CarIssueController(CarIssueService carIssueService) {
    this.carIssueService = carIssueService;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CarIssueCreateResponse> createIssue(
      @PathVariable Long carId,
      @Valid @RequestPart("issue") CarIssueCreateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws IOException {

    Long userId = Long.valueOf(authentication.getName());

    CarIssueCreateResponse response = carIssueService.createIssue(carId, userId, request, image);

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

  @GetMapping("{id}")
  public ResponseEntity<CarIssueDetailResponse> getIssue(
      @PathVariable Long carId,
      @PathVariable Long id,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    CarIssueDetailResponse response = carIssueService.getIssue(carId, id, userId);

    return ResponseEntity.ok(response);
  }

  @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CarIssueUpdateResponse> updateIssue(
      @PathVariable Long carId,
      @PathVariable Long id,
      @Valid @RequestPart("issue") CarIssueUpdateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws IOException {

    Long userId = Long.valueOf(authentication.getName());

    CarIssueUpdateResponse response = carIssueService.updateIssue(
        carId,
        id,
        userId,
        request,
        image);

    return ResponseEntity.ok(response);
  }
}