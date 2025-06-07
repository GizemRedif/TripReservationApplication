
package reservation.service;

import java.util.Collections;
import java.util.List;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import searchCriteria.ReservationSearchCriteria;
import user.model.User;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    
    public boolean createReservation(User user,Reservation reservation){
        if(user == null ||reservation == null){return false;}
        
        checkReservationInfo(reservation);
        reservationRepository.addReservation(user, reservation);
        return true;
    }    
    
    public boolean deleteReservation(Reservation reservation){
        return reservationRepository.deleteReservation(reservation);    
    }
    
    //Kullanıcının rezervasyonlarını getir.
    public List<Reservation> getReservationsByUser(User user) {
        if (user == null) return Collections.emptyList();

        List<Reservation> list = reservationRepository.getReservationListByUser(user);
        return list != null ? list : Collections.emptyList();
    }
     
    //Kriterlere göre arama yap.
    public List<Reservation> searchReservations(ReservationSearchCriteria criteria) {
        if (criteria == null || criteria.getUser() == null) return Collections.emptyList();

        return reservationRepository.search(criteria);
    }
    
    public void checkReservationInfo(Reservation reservation){
        if(reservation.getFare()<0){
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        if(reservation.getPassenger() == null){
            throw new IllegalArgumentException("Passenger cannot be null.");
        }
        if(reservation.getSeat() == null){
            throw new IllegalArgumentException("Seat cannot be null.");
        }
    }   
}
