package tqs.HW1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tqs.HW1.entities.Restaurant;
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
