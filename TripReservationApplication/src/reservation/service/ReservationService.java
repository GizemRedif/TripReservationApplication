
package reservation.service;

import java.util.Collections;
import java.util.List;

import dto.ReservationDTO;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import dto.ReservationSearchCriteria;
import reservation.factory.ReservationFactory;
import user.model.Passenger;
import user.model.User;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance(); 
    
    // Creates a new reservation with the specified user and reservation details.
    public void createReservation(User user,ReservationDTO reservationDTO){

        Reservation reservation;
        Passenger passenger = (Passenger) user; // The user is cast to the Passenger type.
        ReservationFactory factory = new ReservationFactory();
        
        // A new reservation object is created using the ReservationFactory class.
        reservation = factory.createReservation(passenger, reservationDTO.getFare(), reservationDTO.getSeat(), reservationDTO.getTrip());
        reservationDTO.getSeat().setIsBooked(true); // The seat is marked as booked.
        
        reservationRepository.addReservation(reservation); // The created reservation is added to the repository.
        reservation.getTrip().addReservation(reservation); // The same reservation is also added to the trip's reservation list.
    }    
    
    public void deleteReservation(Reservation reservation){ // The reservation is removed from all the places it is stored.
        reservationRepository.deleteReservation(reservation);
        reservation.getTrip().deleteReservation(reservation);
        reservation.getSeat().setIsBooked(false);
    }
    
    // Returns the reservation list based on the specified criteria
    public List<Reservation> getReservationsByUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("User cannot be null.");
        }

        List<Reservation> list = reservationRepository.getReservationListByUser(user); 
        return list != null ? list : Collections.emptyList(); 
    }
     
    //Kriterlere göre arama yap.
    public List<Reservation> searchReservations(ReservationSearchCriteria criteria) {
        if (criteria == null || criteria.getUser() == null) return Collections.emptyList(); // Returns an empty list if the criteria or user information is null.

        return reservationRepository.search(criteria);  // Returns reservations that match the given criteria.
    }
}
