package tqs.hw1.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tqs.hw1.entities.Reservation;
import tqs.hw1.repository.ReservationRepository;
import tqs.hw1.service.ReservationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private Reservation reservationdeleteID;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample reservation data
        reservation = new Reservation();
        reservation.setToken("abc123");

        reservationdeleteID= new Reservation();
        reservationdeleteID.setId(2L);

        
    }

     @Test
    public void testFindByToken() {
            // Arrange
            when(reservationRepository.findByToken("some-token")).thenReturn(reservation);

            // Act
            Reservation result = reservationService.findByToken("some-token");

            // Assert
            assertNotNull(result);
            assertEquals("abc123", result.getToken());
            assertFalse(result.isUsed());
        }

    @Test
    public void testSaveReservation() {
        // Arrange
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation savedReservation = reservationService.save(reservation);

        // Assert
        assertNotNull(savedReservation);
        assertEquals("abc123", savedReservation.getToken());
        assertFalse(savedReservation.isUsed());

        // Verify that the save method was called on the repository
        verify(reservationRepository, times(1)).save(reservation);
    }
    @Test
    public void testGetAllReservations() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.getAllReservations();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("abc123", result.get(0).getToken());
    }

    @Test
    public void testGetReservationById() {
        // Arrange
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("abc123", result.getToken());
    }

    @Test
    public void testCreateReservation() {
        // Arrange
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation createdReservation = reservationService.createReservation(1L);

        // Assert
        assertNotNull(createdReservation);
        assertEquals("abc123", createdReservation.getToken());
    }
    @Test
    public void testDeleteReservationById() {
        // Arrange
        when(reservationRepository.existsById(2L)).thenReturn(true);

        // Act
        boolean result = reservationService.deleteReservationById(2L);

        // Assert
        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(2L);
    }

    @Test
    public void testDeleteReservationByIdNotFound() {
        // Arrange
        when(reservationRepository.existsById(2L)).thenReturn(false);

        // Act
        boolean result = reservationService.deleteReservationById(2L);

        // Assert
        assertFalse(result);
        verify(reservationRepository, never()).deleteById(2L);
    }
    
    @Test
    public void testDeleteReservationByToken() {
        // Arrange: Mock the repository behavior
        when(reservationRepository.findByToken("abc123")).thenReturn(reservation);
        doNothing().when(reservationRepository).delete(reservation);

        // Act: Call the service method
        boolean result = reservationService.deleteReservationByToken("abc123");

        // Assert: Verify the result
        assertTrue(result); // Reservation should be deleted successfully
        verify(reservationRepository, times(1)).delete(reservation); // Ensure delete was called
    }

}
