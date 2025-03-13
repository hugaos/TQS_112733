package com.example.lab3_2carAPI;

import com.example.lab3_2carAPI.entity.Car;
import com.example.lab3_2carAPI.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Disabled   
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    void tearDown() {
        carRepository.deleteAll(); 
    }

    @Test
    void whenSaveCar_thenCarIsPersisted() {
        Car car = new Car("Tesla", "Model S");
        Car savedCar = carRepository.save(car);

        assertThat(savedCar.getId()).isNotNull();
        assertThat(savedCar.getMaker()).isEqualTo("Tesla");
        assertThat(savedCar.getModel()).isEqualTo("Model S");
    }   

    @Test
    void whenFindById_thenReturnCar() {
        Car car = new Car("Toyota", "Corolla");
        car = carRepository.save(car);

        Optional<Car> foundCar = carRepository.findById(car.getId());

        assertThat(foundCar).isPresent();
        assertThat(foundCar.get().getMaker()).isEqualTo("Toyota");
        assertThat(foundCar.get().getModel()).isEqualTo("Corolla");
    }

    @Test
    void whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("Ford", "Fiesta");
        Car car2 = new Car("Honda", "Civic");

        carRepository.save(car1);
        carRepository.save(car2);

        List<Car> cars = carRepository.findAll();

        assertThat(cars).hasSize(2);
        assertThat(cars).extracting(Car::getMaker).containsExactly("Ford", "Honda");
    }

}
