package com.example.lab3_2carAPI.repository;

import com.example.lab3_2carAPI.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}