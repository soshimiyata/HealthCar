package com.example.healthcar.controller;

import jakarta.validation.Valid;

import com.example.healthcar.dto.part.PartCreateRequest;
import com.example.healthcar.dto.part.PartResponse;
import com.example.healthcar.dto.part.PartListResponse;
import com.example.healthcar.dto.part.PartDetailResponse;
import com.example.healthcar.dto.part.PartUpdateRequest;
import com.example.healthcar.dto.part.PartUpdateResponse;
import com.example.healthcar.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.io.IOException;

@RestController
@RequestMapping("/api/cars/{carId}/parts")
public class PartController {

  private final PartService partService;

  public PartController(PartService partService) {
    this.partService = partService;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<PartResponse> createPart(
      @PathVariable Long carId,
      @Valid @RequestPart("part") PartCreateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws Exception {

    Long userId = Long.valueOf(authentication.getName());

    PartResponse response = partService.createPart(carId, userId, request, image);

    URI location = URI.create(
        "/api/cars/" + carId + "/parts/" + response.getId());

    return ResponseEntity
        .created(location)
        .body(response);
  }

  @GetMapping
  public Page<PartListResponse> getParts(
      @PathVariable Long carId,
      Pageable pageable,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    return partService.findParts(carId, userId, pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PartDetailResponse> getPartDetail(
      @PathVariable Long carId,
      @PathVariable Long id,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    PartDetailResponse response = partService.getPartDetail(carId, userId, id);

    return ResponseEntity.ok(response);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<PartUpdateResponse> updatePart(
      @PathVariable Long carId,
      @PathVariable Long id,
      @Valid @RequestPart("part") PartUpdateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws IOException {

    Long userId = Long.valueOf(authentication.getName());

    PartUpdateResponse response = partService.updatePart(carId, userId, id, request, image);

    return ResponseEntity.ok(response);
  }
}