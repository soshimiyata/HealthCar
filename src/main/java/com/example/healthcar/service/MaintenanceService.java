package com.example.healthcar.service;

import com.example.healthcar.dto.MaintenanceCreateRequest;
import com.example.healthcar.dto.MaintenanceResponse;
import com.example.healthcar.dto.MaintenanceListResponse;
import com.example.healthcar.entity.Maintenance;
import com.example.healthcar.entity.MaintenanceType;
import com.example.healthcar.repository.CarRepository;
import com.example.healthcar.repository.MaintenanceRepository;
import com.example.healthcar.repository.MaintenanceTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

  private final MaintenanceRepository maintenanceRepository;
  private final CarRepository carRepository;
  private final MaintenanceTypeRepository maintenanceTypeRepository;

  public MaintenanceService(
      MaintenanceRepository maintenanceRepository,
      CarRepository carRepository,
      MaintenanceTypeRepository maintenanceTypeRepository) {

    this.maintenanceRepository = maintenanceRepository;
    this.carRepository = carRepository;
    this.maintenanceTypeRepository = maintenanceTypeRepository;
  }

  public MaintenanceResponse createMaintenance(
      Long carId,
      Long userId,
      MaintenanceCreateRequest request) {

    // ① 車両が存在し、かつログインユーザーの所有車両か確認
    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    // ② メンテナンス種別が存在するか確認
    if (!maintenanceTypeRepository.existsById(
        request.getMaintenanceTypeId())) {

      throw new RuntimeException("Maintenance type not found");
    }

    // ③ Maintenance Entityを作成
    Maintenance maintenance = new Maintenance();
    MaintenanceType maintenanceType = maintenanceTypeRepository.findById(request.getMaintenanceTypeId())
        .orElseThrow(() -> new RuntimeException("Maintenance type not found"));

    maintenance.setCarId(carId);
    maintenance.setMaintenanceType(maintenanceType);
    maintenance.setDescription(
        request.getDescription());
    maintenance.setMaintenanceDate(
        request.getMaintenanceDate());
    maintenance.setOdometer(
        request.getOdometer());

    if (request.getCost() != null) {
      maintenance.setCost(request.getCost());
    }

    // ④ DBに保存
    Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

    // ⑤ Response DTOに変換
    return MaintenanceResponse.from(savedMaintenance);
  }

  public Page<MaintenanceListResponse> getMaintenances(
      Long carId,
      Long userId,
      Pageable pageable) {

    // 車両が存在し、かつログインユーザーの所有車か確認
    carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    // メンテナンス一覧取得
    return maintenanceRepository
        .findByCarId(carId, pageable)
        .map(MaintenanceListResponse::from);
  }
}