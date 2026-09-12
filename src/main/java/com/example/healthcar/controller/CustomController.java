package com.example.healthcar.controller;

import jakarta.validation.Valid;

import com.example.healthcar.dto.custom.CustomCreateRequest;
import com.example.healthcar.dto.custom.CustomResponse;
import com.example.healthcar.dto.custom.CustomDetailResponse;
import com.example.healthcar.dto.custom.CustomUpdateRequest;
import com.example.healthcar.service.CustomService;
import com.example.healthcar.dto.custom.CustomListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.io.IOException;

@RestController
@RequestMapping("/api/cars/{carId}/customs")
public class CustomController {

  private final CustomService customService;

  public CustomController(CustomService customService) {
    this.customService = customService;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CustomResponse> createCustom(
      @PathVariable Long carId,
      @Valid @RequestPart("custom") CustomCreateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws Exception {

    Long userId = Long.valueOf(authentication.getName());

    CustomResponse response = customService.createCustom(
        carId,
        userId,
        request,
        image);

    return ResponseEntity
        .created(URI.create(
            "/api/cars/" + carId +
                "/customs/" + response.getId()))
        .body(response);
  }

  @GetMapping
  public ResponseEntity<Page<CustomListResponse>> getCustoms(
      @PathVariable Long carId,
      Pageable pageable,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    Page<CustomListResponse> response = customService.getCustoms(carId, userId, pageable);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{customId}")
  public ResponseEntity<CustomDetailResponse> getCustom(
      @PathVariable Long carId,
      @PathVariable Long customId,
      Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    CustomDetailResponse response = customService.getCustom(customId, carId, userId);

    return ResponseEntity.ok(response);
  }

  @PutMapping(value = "/{customId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CustomResponse> updateCustom(
      @PathVariable Long carId,
      @PathVariable Long customId,
      @Valid @RequestPart("custom") CustomUpdateRequest request,
      @RequestPart(value = "image", required = false) MultipartFile image,
      Authentication authentication) throws IOException {

    Long userId = Long.valueOf(authentication.getName());

    CustomResponse response = customService.updateCustom(
        customId,
        carId,
        userId,
        request,
        image);

    return ResponseEntity.ok(response);
  }
}