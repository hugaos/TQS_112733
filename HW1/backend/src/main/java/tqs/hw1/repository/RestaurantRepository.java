package tqs.hw1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.hw1.entities.Restaurant;
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
