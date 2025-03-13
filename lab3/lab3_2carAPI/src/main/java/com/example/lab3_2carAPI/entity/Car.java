package com.example.lab3_2carAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carid;
    private String maker;
    private String model;

    public Car() {
    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Long getId() {
        return carid;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Car car = (Car) o;
        return Objects.equals(carid, car.carid) &&
                Objects.equals(maker, car.maker) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carid, maker, model);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carid=" + carid +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
