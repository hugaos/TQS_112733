package tqs.hw1.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        // Initialize a Reservation object before each test
        reservation = new Reservation();
    }

    @Test
    public void testSetMealId() {
        // Arrange
        Long mealId = 4L;

        // Act
        reservation.setMealId(mealId);

        // Assert
        assertEquals(mealId, reservation.getMealId(), "The mealId should be set correctly.");
    }

    @Test
    public void testSetTimestamp() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.of(2025, 4, 8, 10, 0, 0, 0);

        // Act
        reservation.setTimestamp(timestamp);

        // Assert
        assertEquals(timestamp, reservation.getTimestamp(), "The timestamp should be set correctly.");
    }
}
