package com.example.lab3_2carAPI;

import com.example.lab3_2carAPI.entity.Car;
import com.example.lab3_2carAPI.repository.CarRepository;
import com.example.lab3_2carAPI.service.CarManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito in this test
class CarManagerServiceTest {

    @Mock
    private CarRepository carRepository; // Mock the repository to avoid real database interactions

    @InjectMocks
    private CarManagerService carManagerService; // Inject the mocked repository into the service

    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = new Car("Toyota", "Corolla");
    }

    @Test
    void whenSaveCar_thenCarIsReturned() {
        when(carRepository.save(Mockito.any(Car.class))).thenReturn(testCar);

        Car savedCar = carManagerService.save(new Car("Toyota", "Corolla"));

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getMaker()).isEqualTo("Toyota");
        assertThat(savedCar.getModel()).isEqualTo("Corolla");

        verify(carRepository, times(1)).save(Mockito.any(Car.class));
    }

    @Test
    void whenFindById_thenReturnCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));

        Optional<Car> foundCar = carManagerService.getCarDetails(1L);

        assertThat(foundCar).isPresent();
        assertThat(foundCar.get().getMaker()).isEqualTo("Toyota");
        assertThat(foundCar.get().getModel()).isEqualTo("Corolla");

        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void whenFindByInvalidId_thenReturnEmpty() {
        when(carRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Car> foundCar = carManagerService.getCarDetails(99L);

        assertThat(foundCar).isEmpty();

        verify(carRepository, times(1)).findById(99L);
    }

    @Test
    void whenFindAll_thenReturnCarList() {
        List<Car> cars = List.of(new Car("Honda", "Civic"), new Car("Ford", "Fiesta"));
        when(carRepository.findAll()).thenReturn(cars);

        List<Car> allCars = carManagerService.getAllCars();

        assertThat(allCars).hasSize(2);
        assertThat(allCars).extracting(Car::getMaker).containsExactly("Honda", "Ford");

        verify(carRepository, times(1)).findAll();
    }
}
