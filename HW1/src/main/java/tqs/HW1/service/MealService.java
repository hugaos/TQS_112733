package tqs.HW1.service;
import org.springframework.stereotype.Service;
import tqs.HW1.entities.Meal;
import tqs.HW1.repository.MealRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getFutureMealsByRestaurant(Long restaurantId) {
        return repository.findByRestaurantIdAndDateAfterOrderByDateAsc(restaurantId, LocalDate.now());
    }

    public Meal getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
