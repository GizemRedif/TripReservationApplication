
package reservation.service;

import java.util.Collections;
import java.util.List;

import dto.ReservationDTO;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import dto.ReservationSearchCriteria;
import reservation.factory.ReservationFactory;
import reservation.model.BusReservation;
import user.model.Passenger;
import user.model.User;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    
    public void createReservation(User user,ReservationDTO reservationDTO){

        Reservation reservation;
        Passenger passenger = (Passenger) user;
        ReservationFactory factory = new ReservationFactory();
        reservation = factory.createReservation(passenger, reservationDTO.getFare(), reservationDTO.getSeat(), reservationDTO.getTrip());
        reservationDTO.getSeat().setIsBooked(true);
        
        reservationRepository.addReservation(reservation);
        reservation.getTrip().addReservation(reservation);
    }    
    
    public void deleteReservation(Reservation reservation){
        reservationRepository.deleteReservation(reservation);
        reservation.getTrip().deleteReservation(reservation);
    }
    
    //Kullanıcının rezervasyonlarını getir.
    public List<Reservation> getReservationsByUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("Usaer cannot be null.");
        }

        List<Reservation> list = reservationRepository.getReservationListByUser(user);
        return list != null ? list : Collections.emptyList();
    }
     
    //Kriterlere göre arama yap.
    public List<Reservation> searchReservations(ReservationSearchCriteria criteria) {
        if (criteria == null || criteria.getUser() == null) return Collections.emptyList();

        return reservationRepository.search(criteria);
    }
}
