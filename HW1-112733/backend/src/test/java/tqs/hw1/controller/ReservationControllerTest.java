package tqs.hw1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tqs.hw1.entities.Reservation;
import tqs.hw1.service.ReservationService;
import tqs.hw1.service.MealService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService; // Mock da dependência ReservationService

    @Mock
    private MealService mealService; // Mock da dependência MealService

    @InjectMocks
    private ReservationController reservationController; // O controlador que vai ser testado

    private MockMvc mockMvc; // Para simular as requisições HTTP

    private Reservation reservation; // Variável para armazenar os dados de uma reserva

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Inicializa o MockMvc com o controlador
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        // Cria um exemplo de reserva
        reservation = new Reservation();
        reservation.setToken("abc123");
        reservation.setUsed(false);
    }

    @Test
    void testGetReservationById() throws Exception {
        // Defina o comportamento do mock para o método getById
        when(reservationService.getById(1L)).thenReturn(reservation);

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc123"))
                .andExpect(jsonPath("$.used").value(false));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).getById(1L);
    }

    @Test
    void testCreateReservation() throws Exception {
        // Defina o comportamento do mock para o método createReservation
        when(reservationService.createReservation(1L)).thenReturn(reservation);

        // Perform a POST request and check the response
        mockMvc.perform(post("/api/reservations")
                .param("mealId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc123"))
                .andExpect(jsonPath("$.used").value(false));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).createReservation(1L);
    }

    @Test
    void testMarkAsUsedReservation() throws Exception {
        // Defina o comportamento do mock para o método findByToken
        when(reservationService.findByToken("abc123")).thenReturn(reservation);

        // Perform a PUT request and check the response
        mockMvc.perform(put("/api/reservations/use/abc123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reserva marcada como usada com sucesso."));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).findByToken("abc123");
        verify(reservationService, times(1)).save(reservation);
    }

    @Test
    void testDeleteReservationById() throws Exception {
        // Defina o comportamento do mock para o método deleteReservationById
        when(reservationService.deleteReservationById(1L)).thenReturn(true);

        // Perform a DELETE request and check the response
        mockMvc.perform(delete("/api/reservations/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reserva excluída com sucesso."));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).deleteReservationById(1L);
    }

    @Test
    void testDeleteReservationByToken() throws Exception {
        // Defina o comportamento do mock para o método deleteReservationByToken
        when(reservationService.deleteReservationByToken("abc123")).thenReturn(true);

        // Perform a DELETE request and check the response
        mockMvc.perform(delete("/api/reservations/token/abc123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reserva excluída com sucesso."));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).deleteReservationByToken("abc123");
    }

    @Test
    void testGetAllReservations() throws Exception {
        // Defina o comportamento do mock para o método getAllReservations
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        // Perform a GET request and check the response
        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].token").value("abc123"))
                .andExpect(jsonPath("$[0].used").value(false));

        // Verifica se o método do serviço foi chamado
        verify(reservationService, times(1)).getAllReservations();
    }
    @Test
    void testMarkAsUsedReservationNotFound() throws Exception {
        when(reservationService.findByToken("nonexistent-token")).thenReturn(null);

        mockMvc.perform(put("/api/reservations/use/nonexistent-token"))
                .andExpect(status().isNotFound()) 
                .andExpect(content().string("Reserva não encontrada.")); 

        verify(reservationService, times(1)).findByToken("nonexistent-token");
        verify(reservationService, never()).save(any(Reservation.class));
    }
    @Test
    void testDeleteReservationByIdNotFound() throws Exception {
        when(reservationService.deleteReservationById(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/reservations/id/{id}", 20L))  
                .andExpect(content().string("Reserva não encontrada."));  

        verify(reservationService, times(1)).deleteReservationById(20L);
    }   
    @Test
    void testDeleteReservationByTokenNotFound() throws Exception {
        when(reservationService.deleteReservationByToken(anyString())).thenReturn(false);

        mockMvc.perform(delete("/api/reservations/token/{token}", "invalid-token"))  
                .andExpect(status().isNotFound())  
                .andExpect(content().string("Reserva não encontrada."));  

        verify(reservationService, times(1)).deleteReservationByToken("invalid-token");
    }


}   
