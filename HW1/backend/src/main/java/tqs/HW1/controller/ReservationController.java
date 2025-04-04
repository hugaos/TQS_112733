package tqs.HW1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tqs.HW1.entities.Meal;
import tqs.HW1.entities.Reservation;
import tqs.HW1.service.MealService;
import tqs.HW1.service.ReservationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

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

    @PostMapping
    public Reservation createReservation(@RequestParam Long mealId) {
        Meal meal = mealService.getById(mealId); 
        if (meal == null) {
            throw new RuntimeException("Meal not found");
        }

        Reservation reservation = reservationService.createReservation(meal);
        return reservation;
    }
    
    @PutMapping("/checkin/{token}")
    public ResponseEntity<String> checkInReservation(@PathVariable String token) {
        boolean isCheckedIn = reservationService.checkInReservationByToken(token);

        if (isCheckedIn) {
            return ResponseEntity.ok("Reserva marcada como utilizada.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada ou já foi usada.");
        }
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