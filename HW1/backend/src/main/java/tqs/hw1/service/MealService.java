package tqs.hw1.service;

import org.springframework.stereotype.Service;

import tqs.hw1.entities.Meal;
import tqs.hw1.entities.Restaurant;
import tqs.hw1.repository.MealRepository;
import tqs.hw1.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }
    public Meal getById(Long id) {
        return mealRepository.findById(id).orElseThrow();
    }
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
    public List<Meal> getMealsByRestaurantId(Long restaurantId) {
        return mealRepository.findByRestaurantId(restaurantId);
    }   
}
