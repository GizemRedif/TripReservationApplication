
package reservation.service;

import java.util.Collections;
import java.util.List;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import dto.ReservationSearchCriteria;
import trip.model.Trip;
import user.model.User;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    
    public void createReservation(User user,Reservation reservation){
        if(reservation.getFare()<0){
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        
        reservationRepository.addReservation(reservation);
        //burada aynı zamanda ilgili Trip nesnesinin rezervasyon listesi içine de bu rezervasyonu eklemeliyiz
    }    
    
    public boolean deleteReservation(Reservation reservation){
        return reservationRepository.deleteReservation(reservation);
        //burada da aynı şekilde tripten silmeliyiz
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
