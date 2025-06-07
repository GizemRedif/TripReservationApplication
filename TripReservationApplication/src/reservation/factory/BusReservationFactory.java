
package reservation.factory;

import reservation.model.BusReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import vehicle.model.Seat;
import trip.model.BusTrip;

/**
 *
 * @author hp
 */
public class BusReservationFactory extends ReservationFactory <BusTrip> {
    
    public Reservation createReservation(Passenger passenger, int fare, Seat seat, BusTrip info){
        return new BusReservation(passenger, fare, seat, info);
    }
}
