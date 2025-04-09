package tqs.hw1.service;
import org.springframework.stereotype.Service;

import tqs.hw1.entities.Restaurant;
import tqs.hw1.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }
    public Restaurant createRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Restaurant getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
    public boolean deleteRestaurantById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
