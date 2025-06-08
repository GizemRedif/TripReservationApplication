package reservation.factory;

import reservation.model.BusReservation;
import reservation.model.FlightReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
import vehicle.model.Seat;

public class ReservationFactory {
    public  Reservation createReservation(Passenger passenger, int fare, Seat seat, Trip trip){
        if(trip == null){
            throw new IllegalArgumentException("trip can not be null");
        }
        
        else if(trip instanceof BusTrip){
            return new BusReservation(passenger , fare , seat , (BusTrip)trip);
        }
            return new FlightReservation(passenger , fare , seat , (FlightTrip)trip);
    }
}
