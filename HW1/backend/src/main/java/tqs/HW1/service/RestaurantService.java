package tqs.HW1.service;
import org.springframework.stereotype.Service;
import tqs.HW1.entities.Restaurant;
import tqs.HW1.repository.RestaurantRepository;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }
    
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Restaurant getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
