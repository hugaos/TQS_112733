package tqs.hw1.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RestaurantTest {

    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private Restaurant restaurant3;

    @BeforeEach
    public void setUp() {
        // Initialize Restaurant objects before each test
        restaurant1 = new Restaurant("Cantina Crasto");
        restaurant1.setId(1L);

        restaurant2 = new Restaurant("Cantina Crasto");
        restaurant2.setId(1L);

        restaurant3 = new Restaurant("Cantina Santiago");
        restaurant3.setId(2L);
    }

    @Test
    public void testEquals_SameObject() {
        // Same object should be equal to itself
        assertEquals(restaurant1, restaurant1);
    }

    @Test
    public void testEquals_SameData() {
        // Objects with the same data (id) should be equal
        assertEquals(restaurant1, restaurant2);
    }

    @Test
    public void testEquals_DifferentData() {
        // Objects with different data should not be equal
        assertNotEquals(restaurant1, restaurant3);
    }

    @Test
    public void testEquals_Null() {
        // Object should not be equal to null
        assertNotEquals(restaurant1, null);
    }

    @Test
    public void testHashCode_SameData() {
        // Objects with the same data should have the same hashCode
        assertEquals(restaurant1.hashCode(), restaurant2.hashCode());
    }

    @Test
    public void testHashCode_DifferentData() {
        // Objects with different data should have different hashCode
        assertNotEquals(restaurant1.hashCode(), restaurant3.hashCode());
    }
}
