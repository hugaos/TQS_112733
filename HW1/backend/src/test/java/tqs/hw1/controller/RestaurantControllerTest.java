package tqs.hw1.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqs.hw1.entities.Restaurant;
import tqs.hw1.service.RestaurantService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService; // Mock da dependência

    @InjectMocks
    private RestaurantController restaurantController; // O controlador que vai ser testado

    private MockMvc mockMvc; // Para simular as requisições HTTP

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Inicializa o MockMvc com o controlador
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }
    @Test
    public void testCreateRestaurant() throws Exception {
        // Defina o comportamento do mock para o método createRestaurant
        Restaurant restaurant = new Restaurant("Cantina Crasto");
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        // Perform a POST request and check the response
        mockMvc.perform(post("/api/restaurants")
                .contentType("application/json")
                .content("{\"name\":\"Cantina Crasto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cantina Crasto"));

        // Verifica se o método do serviço foi chamado
        verify(restaurantService, times(1)).createRestaurant(any(Restaurant.class));
    }
    @Test
    void testGetRestaurantById() throws Exception {
        // Defina o comportamento do mock para o método getById
        when(restaurantService.getById(1L)).thenReturn(new Restaurant("Cantina Crasto"));

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cantina Crasto"));

        // Verifica se o método do serviço foi chamado
        verify(restaurantService, times(1)).getById(1L);
    }

    @Test
    void testGetAllRestaurants() throws Exception {
        // Defina o comportamento do mock para o método getAllRestaurants
        when(restaurantService.getAllRestaurants()).thenReturn(List.of(
                new Restaurant("Cantina Crasto"),
                new Restaurant("Cantina Santiago")));

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cantina Crasto"))
                .andExpect(jsonPath("$[1].name").value("Cantina Santiago"));

        verify(restaurantService, times(1)).getAllRestaurants();
    }
    @Test
    void testDeleteRestaurantById_Success() throws Exception {
        when(restaurantService.deleteRestaurantById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isOk()) ;
        verify(restaurantService, times(1)).deleteRestaurantById(1L);
    }
}
