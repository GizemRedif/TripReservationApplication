package reservation.factory;

import trip.model.BusTrip;
import trip.model.FlightTrip;
import reservation.model.FlightReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import vehicle.model.Seat;

public class FlightReservationFactory extends ReservationFactory <FlightTrip>{
 
    @Override
    public Reservation createReservation(Passenger passenger, int fare, Seat seat, FlightTrip info){
        return new FlightReservation(passenger, fare, seat, info);
    }
}
