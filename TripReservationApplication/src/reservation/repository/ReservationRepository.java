package reservation.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import reservation.model.Reservation;
import user.model.User;
import searchCriteria.ReservationSearchCriteria;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import reservation.model.BusReservation;
import reservation.model.FlightReservation;
import trip.model.BusTrip;
import trip.model.FlightTrip;

public class ReservationRepository {

    private static ReservationRepository instance;
    private final Map<User, List<Reservation>> reservationsWithUser;
    
    public ReservationRepository(){
        reservationsWithUser = new HashMap<>();
    }
    
    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepository();
        }
        return instance;
    }
    
     public List<Reservation> getReservationListByUser(User user){
        return reservationsWithUser.get(user);
    }
    
    /*add new reservation*/
    public boolean addReservation(User user , Reservation reservation){
        if(!reservationsWithUser.containsKey(user)){
            return false;
        }
        
        reservationsWithUser.get(user).add(reservation);
        
        return true;
    }
    
    public boolean deleteReservation(Reservation reservation){
        return true;
    }
    
    public List<Reservation> search(ReservationSearchCriteria criteria) {
        List<Reservation> reservations= reservationsWithUser.get(criteria.getUser());
        
            if (reservations == null) {
        return Collections.emptyList();
    }

    return reservations.stream()
            
         //Trip tipi kontrolü
        .filter(reservation -> {
            if (criteria.getTripType() == null) return true;

            if (reservation instanceof BusReservation) {
                BusTrip trip = ((BusReservation) reservation).getBusTrip();
                return trip != null && criteria.getTripType().isInstance(trip);
            } else if (reservation instanceof FlightReservation) {
                FlightTrip trip = ((FlightReservation) reservation).getFlightTrip();
                return trip != null && criteria.getTripType().isInstance(trip);
            }

            return false;
        })
            
         //Kalkış istasyonu filtreleme
        .filter(reservation -> {
            if (criteria.getDepartureStation() == null) return true;

            String departure = null;

            if (reservation instanceof BusReservation) {
                departure = ((BusReservation) reservation).getBusTrip().getDepartureStation();
            } else if (reservation instanceof FlightReservation) {
                departure = ((FlightReservation) reservation).getFlightTrip().getDepartureStation();
            }

            return departure != null &&
                   departure.equalsIgnoreCase(criteria.getDepartureStation());
        })

        //Varış istasyonu filtreleme
        .filter(reservation -> {
            if (criteria.getArrivalStation() == null) return true;

            String arrival = null;

            if (reservation instanceof BusReservation) {
                arrival = ((BusReservation) reservation).getBusTrip().getArrivalStation();
            } else if (reservation instanceof FlightReservation) {
                arrival = ((FlightReservation) reservation).getFlightTrip().getArrivalStation();
            }

            return arrival != null &&
                   arrival.equalsIgnoreCase(criteria.getArrivalStation());
        })
            
        //Geçmiş, gelecek, hepsi filtrelemesi
         .filter(reservation -> {
            int filter = criteria.getTimeFilter();

            if (filter == ReservationSearchCriteria.ALL_RESERVATIONS) return true;

            LocalDate departureDate = null;

            if (reservation instanceof BusReservation) {
                departureDate = ((BusReservation) reservation).getBusTrip().getDepartureDate().toLocalDate();
            } else if (reservation instanceof FlightReservation) {
                departureDate = ((FlightReservation) reservation).getFlightTrip().getDepartureDate().toLocalDate();
            }

            if (departureDate == null) return false;

            LocalDate today = LocalDate.now();

            if (filter == ReservationSearchCriteria.PAST_RESERVATIONS) {
                return departureDate.isBefore(today);
            }

            if (filter == ReservationSearchCriteria.FUTURE_RESERVATIONS) {
                return departureDate.isAfter(today);
            }

            return true;
        })

        //Ücret filtreleme 
        .filter(reservation -> reservation.getFare() >= criteria.getMinFare())
        .filter(reservation -> reservation.getFare() <= criteria.getMaxFare())

        .collect(Collectors.toList());

    }
    
   
}
