package tqs.hw1.services;

import tqs.hw1.entities.Restaurant;
import tqs.hw1.repository.RestaurantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tqs.hw1.service.RestaurantService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void testCreateRestaurant() {
        Restaurant restaurant = new Restaurant("Test Restaurant");
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        assertEquals("Test Restaurant", createdRestaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }
    @Test
    void testGetRestaurantById() {
        Restaurant restaurant = new Restaurant("Test Restaurant");
        restaurant.setId(1L);
        when(restaurantRepository.findById(1L)).thenReturn(java.util.Optional.of(restaurant));

        Restaurant foundRestaurant = restaurantService.getById(1L);

        assertEquals("Test Restaurant", foundRestaurant.getName());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllRestaurants() {
        Restaurant restaurant1 = new Restaurant("Test Restaurant 1");
        Restaurant restaurant2 = new Restaurant("Test Restaurant 2");
        when(restaurantRepository.findAll()).thenReturn(java.util.List.of(restaurant1, restaurant2));

        java.util.List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        assertEquals(2, restaurants.size());
        assertEquals("Test Restaurant 1", restaurants.get(0).getName());
        assertEquals("Test Restaurant 2", restaurants.get(1).getName());
        verify(restaurantRepository, times(1)).findAll();
    }
    
    @Test
    public void testDeleteRestaurantById_Success() {
        // Arrange: Mock behavior for an existing restaurant
        when(restaurantRepository.existsById(1L)).thenReturn(true);

        // Act: Call the method to delete the restaurant
        boolean result = restaurantService.deleteRestaurantById(1L);

        // Assert: Check if the method returned true, and deletion was attempted
        assertTrue(result);
        verify(restaurantRepository, times(1)).deleteById(1L);
    }
    
    @Test
    public void testDeleteRestaurantById_NotFound() {
        // Arrange: Mock behavior for a non-existing restaurant
        when(restaurantRepository.existsById(1L)).thenReturn(false);

        // Act: Call the method to delete the restaurant
        boolean result = restaurantService.deleteRestaurantById(1L);

        // Assert: Check if the method returned false, and deletion was not attempted
        assertFalse(result);
        verify(restaurantRepository, never()).deleteById(1L);
    }
}
