package com.example.lab3_2carAPI;

import com.example.lab3_2carAPI.controller.CarController;
import com.example.lab3_2carAPI.entity.*;
import com.example.lab3_2carAPI.service.CarManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarManagerService carManagerService;
    @InjectMocks
    private CarController carController;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build(); 
    }

    @Test
    void shouldReturnAllCars() throws Exception {
        List<Car> cars = List.of(new Car("Toyota", "Corolla"), new Car("Honda", "Civic"));

        when(carManagerService.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].maker").value("Toyota"))
                .andExpect(jsonPath("$[1].maker").value("Honda"));
    }

    @Test
    void shouldReturnCarById() throws Exception {
        Car car = new Car("Tesla", "Model S");

        when(carManagerService.getCarDetails(1L)).thenReturn(Optional.of(car));

        mockMvc.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker").value("Tesla"))
                .andExpect(jsonPath("$.model").value("Model S"));
    }

    @Test
    void shouldReturnNotFoundForInvalidCarId() throws Exception {
        when(carManagerService.getCarDetails(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cars/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateCar() throws Exception {
        Car car = new Car("Ford", "Focus");

        when(carManagerService.save(Mockito.any())).thenReturn(car);

        mockMvc.perform(post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"maker\":\"Ford\", \"model\":\"Focus\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker").value("Ford"))
                .andExpect(jsonPath("$.model").value("Focus"));
    }
}
