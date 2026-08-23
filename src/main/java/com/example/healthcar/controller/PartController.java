package com.example.healthcar.controller;

import com.example.healthcar.dto.PartCreateRequest;
import com.example.healthcar.dto.PartResponse;
import com.example.healthcar.dto.PartListResponse;
import com.example.healthcar.dto.PartDetailResponse;
import com.example.healthcar.dto.PartUpdateRequest;
import com.example.healthcar.dto.PartUpdateResponse;
import com.example.healthcar.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cars/{carId}/parts")
public class PartController {

  private final PartService partService;

  public PartController(PartService partService) {
    this.partService = partService;
  }

  @PostMapping
  public ResponseEntity<PartResponse> createPart(
      @PathVariable Long carId,
      @RequestBody PartCreateRequest request,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    PartResponse response = partService.createPart(carId, userId, request);

    URI location = URI.create(
        "/api/cars/" + carId + "/parts/" + response.getId());

    return ResponseEntity
        .created(location)
        .body(response);
  }

  @GetMapping
  public Page<PartListResponse> getParts(
      @PathVariable Long carId,
      Pageable pageable) {

    return partService.findParts(carId, pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PartDetailResponse> getPartDetail(
      @PathVariable Long carId,
      @PathVariable Long id) {

    PartDetailResponse response = partService.getPartDetail(carId, id);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PartUpdateResponse> updatePart(
      @PathVariable Long carId,
      @PathVariable Long id,
      @RequestBody PartUpdateRequest request) {

    PartUpdateResponse response = partService.updatePart(carId, id, request);

    return ResponseEntity.ok(response);
  }
}