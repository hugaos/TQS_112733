package com.example.lab3_2carAPI.controller;

import com.example.lab3_2carAPI.entity.Car;
import com.example.lab3_2carAPI.service.CarManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carManagerService.getAllCars();
        return ResponseEntity.ok(cars); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carManagerService.getCarDetails(id);
        return car.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());     
    }
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car savedCar = carManagerService.save(car);
        return ResponseEntity.status(201).body(savedCar); 
    }
}
