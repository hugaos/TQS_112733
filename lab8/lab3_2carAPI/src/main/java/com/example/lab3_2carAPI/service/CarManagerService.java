package com.example.lab3_2carAPI.service;

import com.example.lab3_2carAPI.entity.Car;
import com.example.lab3_2carAPI.repository.CarRepository;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Optional;
@Entity
public class CarManagerService {
    private CarRepository carRepository;
    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id) {
        return carRepository.findById(id);
    }
}
