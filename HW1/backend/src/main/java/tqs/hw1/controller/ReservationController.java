package tqs.hw1.controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tqs.hw1.entities.Meal;
import tqs.hw1.entities.Reservation;
import tqs.hw1.service.MealService;
import tqs.hw1.service.ReservationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController 
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {
    private final ReservationService reservationService;
    private final MealService mealService;

    public ReservationController(ReservationService reservationService, MealService mealService) {
        this.reservationService = reservationService;
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getById(id);
    }
    @PostMapping
    public Reservation createReservation(@RequestParam Long mealId) {
        
        return reservationService.createReservation(mealId);
        
    }
    
    @PutMapping("/use/{token}")
        public ResponseEntity<String> markAsUsedReservation(@PathVariable String token) {
            // Encontra a reserva pelo token
            Reservation reservation = reservationService.findByToken(token);
            
            // Verifica se a reserva existe
            if (reservation == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reserva não encontrada.");
            }

            // Marca a reserva como usada
            reservation.setUsed(true);
            
            // Salva a reserva com o campo 'used' atualizado
            reservationService.save(reservation);

            // Retorna uma resposta com sucesso
            return ResponseEntity.ok("Reserva marcada como usada com sucesso.");
        }

    // Método DELETE para excluir uma reserva por ID
    @DeleteMapping("/id/{id}")
    public String deleteReservationById(@PathVariable Long id) {
        boolean isDeleted = reservationService.deleteReservationById(id);
        if (isDeleted) {
            return "Reserva excluída com sucesso.";
        } else {
            return "Reserva não encontrada.";
        }
    }
    //get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
    // Método DELETE para excluir uma reserva por token
    @DeleteMapping("/token/{token}")
    public ResponseEntity<String> deleteReservationByToken(@PathVariable String token) {
        boolean isDeleted = reservationService.deleteReservationByToken(token);
        if (isDeleted) {
            return ResponseEntity.ok("Reserva excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada.");
        }
    }
}