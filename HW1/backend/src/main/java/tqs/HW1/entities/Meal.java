package tqs.HW1.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;

@Entity
public class Meal {
    @Id 
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private LocalDate date;

    @ManyToOne
    @JsonBackReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;
    //get e set
    public Meal() {
    }

    public Meal(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    
}
