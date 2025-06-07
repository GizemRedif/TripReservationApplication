package reservation.repository;

import reservation.model.Reservation;
import user.model.User;
import searchCriteria.ReservationSearchCriteria;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
    
//    public List<Reservation> search(ReservationSearchCriteria criteria) {
//        return reservationsWithUser.stream()
//
//            .filter(t -> criteria.getTripType() == null || criteria.getTripType().isInstance(t))
//
//            .filter(t -> criteria.getDepartureStation() == null || 
//                         t.getDepartureStation().equalsIgnoreCase(criteria.getDepartureStation()))
//            .filter(t -> criteria.getArrivalStation() == null || 
//                         t.getArrivalStation().equalsIgnoreCase(criteria.getArrivalStation()))
//
//            .filter(t -> criteria.getDepartureTimeFrom() == null || 
//                         !t.getDepartureDate().isBefore(criteria.getDepartureTimeFrom()))
//            .filter(t -> criteria.getDepartureTimeTo() == null || 
//                         !t.getDepartureDate().isAfter(criteria.getDepartureTimeTo()))
//
//            .filter(t -> criteria.getMinFare() == null || t.getFare() >= criteria.getMinFare())
//            .filter(t -> criteria.getMaxFare() == null || t.getFare() <= criteria.getMaxFare())
//
//            .collect(Collectors.toList());
//    }
}
