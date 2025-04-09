package tqs.hw1.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tqs.hw1.entities.Meal;
import tqs.hw1.repository.MealRepository;
import tqs.hw1.service.MealService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    private Meal meal;
    private Long restaurantId = 1L; // Assume this is the restaurant ID

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample meal data
        meal = new Meal("Bacalhau com Natas", LocalDate.of(2025, 4, 8), restaurantId);
    }

    @Test
    public void testGetAllMeals() {
        // Arrange
        List<Meal> meals = Arrays.asList(meal);
        when(mealRepository.findAll()).thenReturn(meals);

        // Act
        List<Meal> result = mealService.getAllMeals();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Bacalhau com Natas", result.get(0).getName());
        assertEquals(restaurantId, result.get(0).getRestaurantId()); // Verifying restaurantId association
    }

    @Test
    public void testGetMealById() {
        // Arrange
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        // Act
        Meal result = mealService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Bacalhau com Natas", result.getName());
        assertEquals(restaurantId, result.getRestaurantId()); // Verifying the correct restaurant ID
    }

    @Test
    public void testCreateMeal() {
        // Arrange
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);

        // Act
        Meal createdMeal = mealService.createMeal(meal);

        // Assert
        assertNotNull(createdMeal);
        assertEquals("Bacalhau com Natas", createdMeal.getName());
        assertEquals(restaurantId, createdMeal.getRestaurantId()); // Verifying the correct restaurant ID
    }
    @Test
    public void testGetMealsByRestaurantId() {
        // Arrange
        List<Meal> meals = Arrays.asList(meal);
        when(mealRepository.findByRestaurantId(1L)).thenReturn(meals);

        // Act
        List<Meal> result = mealService.getMealsByRestaurantId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(meal.getName(), result.get(0).getName());
        verify(mealRepository, times(1)).findByRestaurantId(1L);
    }
}
