package tqs.HW1.controller;

import org.springframework.stereotype.Controller;

import tqs.HW1.service.RestaurantService;

@Controller
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }
}
