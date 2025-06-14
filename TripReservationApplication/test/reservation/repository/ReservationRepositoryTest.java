package reservation.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import reservation.model.BusReservation;
import reservation.model.Reservation;
import seat.BusSeat;
import trip.model.BusTrip;
import user.model.Passenger;
import user.model.User;
import vehicle.model.Bus;

public class ReservationRepositoryTest {
    
    //Singleton testi.
    @Test
    public void testSingletonInstance(){
        ReservationRepository r1= ReservationRepository.getInstance();
        ReservationRepository r2= ReservationRepository.getInstance();
        assertSame(r1,r2);
    }

    @Test
    public void testGetReservationListByUser() {
       ReservationRepository repository = ReservationRepository.getInstance();

        Passenger user = new Passenger("Gizem", "Redif", "gizem@gmail.com", "gizem123", "05551218877", 'F', "kart");
        getInternalMap(repository).put(user, new ArrayList<>());
        List<Reservation> list = repository.getReservationListByUser(user);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testAddReservationSuccess() {
        ReservationRepository repository = ReservationRepository.getInstance();
        
        Passenger user = new Passenger("Mert", "Berrak", "mert@gmail.com", "mert123", "05456669988", 'M', "kart1");
        double fare= 300;
        BusSeat seat= new BusSeat("10",false);
        LocalDateTime departureDate= LocalDateTime.of(2025,6,25,10,30);
        LocalTime tripTime = LocalTime.of(6,30);
        Bus bus= new Bus("34 ABC 123");
        BusTrip trip = new BusTrip("İzmir", "İstanbul", departureDate, tripTime, fare, bus);
        Reservation busReservation = new BusReservation(user,fare,seat,trip);

        repository.addReservation(busReservation); 
        List<Reservation> userReservations = repository.getReservationListByUser(user); 

        assertNotNull("The user's reservation list should not be null.", userReservations);
        assertEquals("The reservation list should contain exactly 1 reservation.", 1, userReservations.size()); 
        assertTrue("The added reservation should be in the list.", userReservations.contains(busReservation));
        assertEquals("The added reservation should be the expected reservation.", busReservation, userReservations.get(0));

    }

    @Test
    public void testDeleteReservation() {
       ReservationRepository repository = ReservationRepository.getInstance();
        
        Passenger user = new Passenger("Mert", "Berrak", "mert@gmail.com", "mert123", "05456669988", 'M', "kart1");
        double fare= 300;
        BusSeat seat= new BusSeat("10",false);
        LocalDateTime departureDate= LocalDateTime.of(2025,6,25,10,30);
        LocalTime tripTime = LocalTime.of(6,30);
        Bus bus= new Bus("34 ABC 123");
        BusTrip trip = new BusTrip("İzmir", "İstanbul", departureDate, tripTime, fare, bus);
        Reservation busReservation = new BusReservation(user,fare,seat,trip);

        repository.addReservation(busReservation); 
        boolean result = repository.deleteReservation(busReservation);        
        assertTrue(result);
        
    }
    
    private Map<User, List<Reservation>> getInternalMap(ReservationRepository repository) {
        try {
            java.lang.reflect.Field field = ReservationRepository.class.getDeclaredField("reservationsWithUser");
            field.setAccessible(true);
            return (Map<User, List<Reservation>>) field.get(repository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}