package tqs.hw1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import tqs.hw1.service.MealService;
import tqs.hw1.entities.Meal;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MealController {
    
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
    // create a meal, with a string name, a date, and a restaurant id
    @PostMapping("/meals")
    public Meal createMeal(@RequestBody Meal meal) {
        return mealService.createMeal(meal);
    }
    // get all meals
    @GetMapping("/meals")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @GetMapping("/{restaurantId}/meals")
    public List<Meal> getMealsByRestaurantId(@PathVariable Long restaurantId) {
        return mealService.getMealsByRestaurantId(restaurantId);
    }
    @GetMapping("/meals/{mealId}")
    public Meal getMealById(@PathVariable Long mealId) {
        return mealService.getById(mealId);
    }

}