package com.example.healthcar.service;

import com.example.healthcar.dto.CarCreateRequest;
import com.example.healthcar.dto.CarResponse;
import com.example.healthcar.dto.CarDetailResponse;
import com.example.healthcar.dto.CarUpdateRequest;
import com.example.healthcar.entity.Car;
import com.example.healthcar.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private final CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
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

    Car savedCar = carRepository.save(car);

    return new CarResponse(
        savedCar.getId(),
        savedCar.getMaker(),
        savedCar.getCarModel());
  }

  public Page<CarResponse> getCars(
      Long userId,
      Pageable pageable) {

    return carRepository
        .findByUserId(userId, pageable)
        .map(car -> new CarResponse(
            car.getId(),
            car.getMaker(),
            car.getCarModel()));
  }

  public CarDetailResponse getCar(Long carId, Long userId) {
    Car car = carRepository.findByIdAndUserId(carId, userId)
        .orElseThrow(() -> new RuntimeException("Car not found"));

    return CarDetailResponse.from(car);
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

    Car updatedCar = carRepository.save(car);

    return new CarResponse(
        updatedCar.getId(),
        updatedCar.getMaker(),
        updatedCar.getCarModel());
  }
}