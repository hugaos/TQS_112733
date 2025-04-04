package tqs.HW1.service;

import org.springframework.stereotype.Service;
import tqs.HW1.entities.*;
import tqs.HW1.repository.MealRepository;
import tqs.HW1.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final MealRepository mealRepo;
    
    public ReservationService(ReservationRepository reservationRepo, MealRepository mealRepo) {
        this.reservationRepo = reservationRepo;
        this.mealRepo = mealRepo;
    }
    public Reservation createReservation(Meal meal) {
        Reservation reservation = new Reservation(meal);
        reservationRepo.save(reservation);
        return reservation;
    }
    
    public boolean deleteReservationById(Long id) {
        if (reservationRepo.existsById(id)) {
            reservationRepo.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean deleteReservationByToken(String token) {
        Reservation reservation = reservationRepo.findByToken(token);
        if (reservation != null) {
            reservationRepo.delete(reservation);
            return true;
        }
        return false;
    }
    
    public boolean checkInReservationByToken(String token) {
        Reservation reservation = reservationRepository.findByToken(token);

        if (reservation != null && !reservation.isUsed()) {
            reservation.setUsed(true); 
            reservationRepository.save(reservation);
            return true;
        }
        return false; 
    }
}
