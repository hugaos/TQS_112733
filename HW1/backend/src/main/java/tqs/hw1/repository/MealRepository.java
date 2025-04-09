package tqs.hw1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.hw1.entities.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByRestaurantIdAndDateAfterOrderByDateAsc(Long restaurantId, LocalDate date);

    List<Meal> findByRestaurantId(Long restaurantId);
}