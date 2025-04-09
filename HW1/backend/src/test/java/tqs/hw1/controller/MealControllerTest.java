package tqs.hw1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqs.hw1.entities.Meal;
import tqs.hw1.service.MealService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

public class MealControllerTest {

    @Mock
    private MealService mealService; // Mock da dependência MealService

    @InjectMocks
    private MealController mealController; // O controlador que vai ser testado

    private MockMvc mockMvc; // Para simular as requisições HTTP

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Inicializa o MockMvc com o controlador
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
    }

    @Test
    public void testGetMealById() throws Exception {
        // Defina o comportamento do mock para o método getById
        Meal meal = new Meal("Bacalhau com Natas", LocalDate.of(2025, 4, 8), 1L);
        when(mealService.getById(1L)).thenReturn(meal);

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/meals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bacalhau com Natas"))
                .andExpect(jsonPath("$.date").value("2025-04-08"))
                .andExpect(jsonPath("$.restaurantId").value(1L));

        // Verifica se o método do serviço foi chamado
        verify(mealService, times(1)).getById(1L);
    }
    @Test
    public void testGetAllMeals() throws Exception {
        // Defina o comportamento do mock para o método getAllMeals
        Meal meal1 = new Meal("Bacalhau com Natas", LocalDate.of(2025, 4, 8), 1L);
        Meal meal2 = new Meal("Frango Assado", LocalDate.of(2025, 4, 9), 2L);
        when(mealService.getAllMeals()).thenReturn(List.of(meal1, meal2));

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bacalhau com Natas"))
                .andExpect(jsonPath("$[1].name").value("Frango Assado"));

        // Verifica se o método do serviço foi chamado
        verify(mealService, times(1)).getAllMeals();
    }

    @Test
    public void testCreateMeal() throws Exception {
        // Arrange
        Meal meal = new Meal("Bacalhau com Natas", LocalDate.of(2025, 4, 8), 1L);
        when(mealService.createMeal(any(Meal.class))).thenReturn(meal);

        // Act and Assert: realizando o teste de criação da refeição
        mockMvc.perform(post("/api/meals")
                .contentType("application/json")
                .content("{\"name\":\"Bacalhau com Natas\",\"date\":\"2025-04-08\",\"restaurantId\":1}"))
                .andExpect(status().isOk()) // Espera um status 201
                .andExpect(jsonPath("$.name").value("Bacalhau com Natas")) // Verifica o nome
                .andExpect(jsonPath("$.date").value("2025-04-08")) // Verifica a data
                .andExpect(jsonPath("$.restaurantId").value(1L)); // Verifica o id do restaurante
    }
    
}