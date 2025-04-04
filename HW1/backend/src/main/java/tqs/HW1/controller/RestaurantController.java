package tqs.HW1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.HW1.entities.Restaurant;
import tqs.HW1.service.RestaurantService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/api/restaurants")
    public List<Restaurant> getRestaurants() {
        return service.getAllRestaurants();
    }

}
