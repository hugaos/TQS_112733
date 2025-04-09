package tqs.hw1.entities;

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
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;

@Entity
public class Meal {
    @Id 
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd") 
    private LocalDate date;

    private Long restaurantId;

    public Meal() {
    }

    public Meal(String name, LocalDate date, Long restaurantId) {
        this.name = name;
        this.date = date;
        this.restaurantId = restaurantId;
    }
    public Long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

    
}
