package tqs.HW1.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Reservation {
    @Id @GeneratedValue
    private Long id;

    private String token;

    @ManyToOne
    private Meal meal;

    private boolean used;

    private LocalDateTime timestamp;
}
