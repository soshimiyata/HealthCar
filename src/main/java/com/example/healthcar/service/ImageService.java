package com.example.healthcar.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  public String saveImage(
      MultipartFile image,
      String directory) throws IOException {

    if (image == null || image.isEmpty()) {
      return null;
    }

    Path uploadDir = Paths.get("uploads", directory);
    Files.createDirectories(uploadDir);

    String extension = "";
    String originalFilename = image.getOriginalFilename();

    if (originalFilename != null) {
      int dotIndex = originalFilename.lastIndexOf('.');

      if (dotIndex >= 0) {
        extension = originalFilename.substring(dotIndex);
      }
    }

    String fileName = UUID.randomUUID() + extension;

    Path filePath = uploadDir.resolve(fileName);

    image.transferTo(filePath);

    return "/uploads/" + directory + "/" + fileName;
  }
}