package tqs.hw1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tqs.hw1.entities.*;
import tqs.hw1.repository.MealRepository;
import tqs.hw1.repository.ReservationRepository;  

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final MealRepository mealRepo;
    
    public ReservationService(ReservationRepository reservationRepo, MealRepository mealRepo) {
        this.reservationRepo = reservationRepo;
        this.mealRepo = mealRepo;
    }
    public Reservation createReservation(Long mealId) {
        Reservation reservation = new Reservation(mealId);
        return reservationRepo.save(reservation);
    }
    public Reservation getById(Long id) {
        return reservationRepo.findById(id).orElseThrow();
    }
    
    public boolean deleteReservationById(Long id) {
        if (reservationRepo.existsById(id)) {
            reservationRepo.deleteById(id);
            return true;
        }
        return false;
    }   
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }
    public boolean deleteReservationByToken(String token) {
        Reservation reservation = reservationRepo.findByToken(token);
        if (reservation != null) {
            reservationRepo.delete(reservation);
            return true;
        }
        return false;
    }
    
    public Reservation findByToken(String token) {
        return reservationRepo.findByToken(token);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }
}
