package com.example.healthcar.service;

import java.util.List;
import com.example.healthcar.dto.car.CarCreateRequest;
import com.example.healthcar.dto.car.CarResponse;
import com.example.healthcar.dto.car.CarDetailResponse;
import com.example.healthcar.dto.car.CarUpdateRequest;
import com.example.healthcar.entity.Car;
import com.example.healthcar.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.healthcar.repository.MaintenanceRepository;
import com.example.healthcar.repository.CustomRepository;
import com.example.healthcar.repository.PartRepository;
import com.example.healthcar.repository.CarIssueRepository;
import com.example.healthcar.dto.maintenance.MaintenanceListResponse;
import com.example.healthcar.dto.custom.CustomListResponse;
import com.example.healthcar.dto.part.PartListResponse;
import com.example.healthcar.dto.carissue.CarIssueListResponse;
import com.example.healthcar.dto.car.CarSummaryResponse;
import com.example.healthcar.dto.summary.SummaryData;

@Service
public class CarService {

  private final CarRepository carRepository;
  private final MaintenanceRepository maintenanceRepository;
  private final CustomRepository customRepository;
  private final PartRepository partRepository;
  private final CarIssueRepository carIssueRepository;

  public CarService(
      CarRepository carRepository,
      MaintenanceRepository maintenanceRepository,
      CustomRepository customRepository,
      PartRepository partRepository,
      CarIssueRepository carIssueRepository) {

    this.carRepository = carRepository;
    this.maintenanceRepository = maintenanceRepository;
    this.customRepository = customRepository;
    this.partRepository = partRepository;
    this.carIssueRepository = carIssueRepository;
  }

  public CarResponse createCar(
      Long userId,
      CarCreateRequest request) {

    Car car = new Car();

    car.setUserId(userId);
    car.setMaker(request.getMaker());
    car.setCarModel(request.getCarModel());
    car.setModelYear(request.getModelYear());
    car.setDescription(request.getDescription());
    car.setOdometer(request.getOdometer());
    car.setStatus(request.getStatus());
    car.setImageUrl(request.getImageUrl());

    Car savedCar = carRepository.save(car);

    return new CarResponse(
        savedCar.getId(),
        savedCar.getMaker(),
        savedCar.getCarModel(),
        savedCar.getImageUrl());
  }

  public Page<CarResponse> getCars(
      Long userId,
      Pageable pageable) {

    return carRepository
        .findByUserId(userId, pageable)
        .map(car -> new CarResponse(
            car.getId(),
            car.getMaker(),
            car.getCarModel(),
            car.getImageUrl()));
  }

  public CarDetailResponse getCar(Long carId, Long userId) {

    Car car = carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    // 最近のデータ
    List<MaintenanceListResponse> recentMaintenances = maintenanceRepository
        .findTop3ByCarIdOrderByMaintenanceDateDesc(carId)
        .stream()
        .map(MaintenanceListResponse::from)
        .toList();

    List<CustomListResponse> recentCustoms = customRepository
        .findTop3ByCarIdOrderByCustomDateDesc(carId)
        .stream()
        .map(CustomListResponse::from)
        .toList();

    List<PartListResponse> currentParts = partRepository
        .findTop5ByCarIdAndStatusOrderByInstalledAtDesc(
            carId, (short) 0)
        .stream()
        .map(PartListResponse::from)
        .toList();

    List<CarIssueListResponse> recentCarIssues = carIssueRepository
        .findTop3ByCarIdOrderByOccurredAtDesc(carId)
        .stream()
        .map(CarIssueListResponse::from)
        .toList();

    // summary
    SummaryData maintenanceSummary = maintenanceRepository.findSummary(carId);

    int maintenanceCount = maintenanceSummary.getCount().intValue();
    int maintenanceCost = maintenanceSummary.getCost().intValue();

    SummaryData customSummary = customRepository.findSummary(carId);

    System.out.println("customSummary: " + customSummary.getCount() + ", " + customSummary.getCost());
    int customCount = customSummary.getCount().intValue();
    int customCost = customSummary.getCost().intValue();

    SummaryData partSummary = partRepository.findSummary(carId);

    System.out.println("partSummary: " + partSummary.getCount() + ", " + partSummary.getCost());
    int partsCount = partSummary.getCount().intValue();
    int partsCost = partSummary.getCost().intValue();

    int totalCost = maintenanceCost + customCost + partsCost;

    CarSummaryResponse summary = new CarSummaryResponse(
        maintenanceCount,
        customCount,
        partsCount,
        maintenanceCost,
        customCost,
        partsCost,
        totalCost);

    return new CarDetailResponse(
        car.getId(),
        car.getMaker(),
        car.getCarModel(),
        car.getModelYear(),
        car.getDescription(),
        car.getOdometer(),
        car.getStatus(),
        car.getImageUrl(),
        summary,
        recentMaintenances,
        recentCustoms,
        currentParts,
        recentCarIssues);
  }

  public CarResponse updateCar(
      Long carId,
      Long userId,
      CarUpdateRequest request) {

    Car car = carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    car.setMaker(request.getMaker());
    car.setCarModel(request.getCarModel());
    car.setModelYear(request.getModelYear());
    car.setDescription(request.getDescription());
    car.setOdometer(request.getOdometer());
    car.setStatus(request.getStatus());
    car.setImageUrl(request.getImageUrl());

    Car updatedCar = carRepository.save(car);

    return new CarResponse(
        updatedCar.getId(),
        updatedCar.getMaker(),
        updatedCar.getCarModel(),
        updatedCar.getImageUrl());
  }
}