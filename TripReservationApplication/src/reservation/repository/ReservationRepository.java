package reservation.repository;

import java.time.LocalDate;
import reservation.model.Reservation;
import user.model.User;
import dto.ReservationSearchCriteria;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

// The ReservationRepository class is a singleton class that stores and manages users' reservations.
public class ReservationRepository {

    private static ReservationRepository instance; // Example of singleton.
    private final Map<User, List<Reservation>> reservationsWithUser; // Stores the reservation list for each user.
    
    public ReservationRepository(){ // Initializes the HashMap that will store the reservations.
        reservationsWithUser = new HashMap<>();
    }
    
    public static ReservationRepository getInstance() {
        if (instance == null) {   // If the singleton instance doesn't exist, it creates one.
            instance = new ReservationRepository();
        }
        return instance; // Returns the singleton instance if it already exists.
    }
    
     public List<Reservation> getReservationListByUser(User user){
        return reservationsWithUser.get(user); // Returns the reservation list for the specified user.
    }
    
    public void addReservation(Reservation reservation){
        User user = (User)reservation.getPassenger(); // Gets the user who made the reservation.
        if(!reservationsWithUser.containsKey(user)){  // If the user has not made a reservation before, a new list is created.
            List<Reservation> usersReservationList = new ArrayList<>();
            usersReservationList.add(reservation);
            reservationsWithUser.put(user, usersReservationList);
        }
        else{
        reservationsWithUser.get(user).add(reservation); // If the user has made reservations before, the new reservation is added to the existing list.
        }
    }
    
    // Removes the specified reservation from the corresponding user's reservation list.
    public boolean deleteReservation(Reservation reservation){
        reservationsWithUser.get(reservation.getPassenger()).remove(reservation);
        
        return true;
    }
    
    // Searches for reservations based on the specified criteria and returns a filtered list.
    public List<Reservation> search(ReservationSearchCriteria criteria) {
        List<Reservation> reservations= reservationsWithUser.get(criteria.getUser());
        
        if (reservations == null) {
            return Collections.emptyList(); // Returns an empty list if the user has no reservations.
        }

        return reservations.stream() // Used to perform filtering operations on the reservation list.
            
         // Filters by trip type.
        .filter(reservation -> criteria.getTripType() == null || criteria.getTripType().isInstance(reservation.getTrip()))      
         
        // Filters by departure station. 
        .filter(reservation -> criteria.getDepartureStation() == null || reservation.getTrip().getDepartureStation().equalsIgnoreCase(criteria.getDepartureStation()))

        //Filters by arrival station.
        .filter(reservation -> criteria.getArrivalStation() == null || reservation.getTrip().getArrivalStation().equalsIgnoreCase(criteria.getArrivalStation()))

        // Filtering past, future, or all reservations.
         .filter(reservation -> {
            int filter = criteria.getTimeFilter();

            if (filter == ReservationSearchCriteria.ALL_RESERVATIONS) return true; //All reservations.

            LocalDate departureDate = reservation.getTrip().getDepartureDate().toLocalDate();
            LocalDate today = LocalDate.now();

            if (filter == ReservationSearchCriteria.PAST_RESERVATIONS) { // Paste reservations.
                return departureDate.isBefore(today);
            }

            if (filter == ReservationSearchCriteria.FUTURE_RESERVATIONS) { // Future reservations.
                return departureDate.isAfter(today);
            }

            return true;
        })

        // Filtering by fare range.
        .filter(reservation -> reservation.getFare() >= criteria.getMinFare())
        .filter(reservation -> reservation.getFare() <= criteria.getMaxFare())

        .collect(Collectors.toList()); // Returns the results as a list.

    }
    
   
}
