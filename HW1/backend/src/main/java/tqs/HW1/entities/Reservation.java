package tqs.HW1.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Reservation {
    @Id 
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String token;

    @ManyToOne
    @JsonBackReference
    private Meal meal;

    private boolean used;

    private LocalDateTime timestamp;

    public Reservation() {
    }

    public Reservation(Meal meal) {
        this.token = UUID.randomUUID().toString();
        this.meal = meal;
        this.used = false;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Meal getMeal() {
        return meal;
    }

    public boolean isUsed() {
        return used;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
