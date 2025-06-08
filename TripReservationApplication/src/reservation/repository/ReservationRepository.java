package reservation.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import reservation.model.Reservation;
import user.model.User;
import dto.ReservationSearchCriteria;

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
        .filter(reservation -> criteria.getTripType() == null || criteria.getTripType().isInstance(reservation.getTrip()))      
         
        //Kalkış istasyonu filtreleme
        .filter(reservation -> criteria.getDepartureStation() == null || reservation.getTrip().getDepartureStation().equalsIgnoreCase(criteria.getDepartureStation()))

        //Varış istasyonu filtreleme
        .filter(reservation -> criteria.getArrivalStation() == null || reservation.getTrip().getArrivalStation().equalsIgnoreCase(criteria.getArrivalStation()))

        //Geçmiş, gelecek, hepsi filtrelemesi
         .filter(reservation -> {
            int filter = criteria.getTimeFilter();

            if (filter == ReservationSearchCriteria.ALL_RESERVATIONS) return true;

            LocalDate departureDate = reservation.getTrip().getDepartureDate().toLocalDate();
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
