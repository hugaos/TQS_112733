package tqs.HW1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tqs.HW1.entities.Reservation;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByToken(String token);
}
