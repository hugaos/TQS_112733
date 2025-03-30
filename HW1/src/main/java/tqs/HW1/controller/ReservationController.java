package tqs.HW1.controller;

import org.springframework.stereotype.Controller;

import tqs.HW1.service.ReservationService;

@Controller 
public class ReservationController {
     private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

}
