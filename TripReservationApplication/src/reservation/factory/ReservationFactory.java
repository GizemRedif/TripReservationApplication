package reservation.factory;

import reservation.model.BusReservation;
import reservation.model.FlightReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import seat.BusSeat;
import seat.PlaneSeat;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
import seat.Seat;

// The ReservationFactory class is a factory class that creates the appropriate type of reservation (bus or flight)
public class ReservationFactory {
    
    // Creates a reservation object based on the given information.
    public  Reservation createReservation(Passenger passenger, double fare, Seat seat, Trip trip){
        
        if(trip instanceof BusTrip){ // If the trip is of type BusTrip, a BusReservation object is created and returned.
            return new BusReservation(passenger , fare , (BusSeat)seat , (BusTrip)trip);
        }
        return new FlightReservation(passenger , fare , (PlaneSeat)seat , (FlightTrip)trip); // If the condition is not met, a FlightReservation object is created and returned by default.
    }
}
