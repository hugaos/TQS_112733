package tqs.HW1.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
public class Meal {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDate date;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
