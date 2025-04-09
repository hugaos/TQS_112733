package tqs.hw1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tqs.hw1.entities.Restaurant;
import tqs.hw1.service.RestaurantService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping("/api/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return service.createRestaurant(restaurant);
    }
    @GetMapping("/api/restaurants")
    public List<Restaurant> getRestaurants() {
        return service.getAllRestaurants();
    }
    @GetMapping("/api/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/api/restaurants/{id}")
    public boolean deleteRestaurantById(@PathVariable Long id) {
        return service.deleteRestaurantById(id);
    }
    
}
