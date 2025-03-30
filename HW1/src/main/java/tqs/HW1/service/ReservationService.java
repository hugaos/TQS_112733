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

}
