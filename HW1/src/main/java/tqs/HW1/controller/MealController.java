package tqs.HW1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tqs.HW1.service.MealService;

@Controller
@RequestMapping("/restaurant")
public class MealController {
    
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
}
