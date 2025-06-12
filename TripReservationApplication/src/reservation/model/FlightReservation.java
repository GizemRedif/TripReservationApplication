package reservation.model;

import seat.PlaneSeat;
import seat.Seat;
import trip.model.FlightTrip;
import trip.model.Trip;
import user.model.Passenger;

public class FlightReservation extends Reservation {
    
    private PlaneSeat seat; // Holds the airplane seat information.
    private FlightTrip flight; // Holds the flight trip information.
    
    public FlightReservation(Passenger passenger, double fare, PlaneSeat seat,FlightTrip flight){
        super(passenger,fare); // Passenger and fare information is taken from the superclass.
        this.flight = flight;
        this.seat = seat;
    }
    
    public Seat getSeat() {
        return seat;
    }

    //Seat information is set. Only objects of type PlaneSeat are accepted.
    public void setSeat(Seat seat) {
        if(seat instanceof PlaneSeat PlaneSeat){
            this.seat = PlaneSeat;
        }
        else{
            throw new IllegalArgumentException("seat typte must be BusSeat"); // An exception is thrown if a seat of the wrong type is assigned.
        }
    }
    
    public Trip getTrip() {
        if(flight !=null){
            return flight;
        }
        else{
            return null;
        }
    }
    
    public void setTrip(Trip trip){
        if(trip == null){
            throw new IllegalArgumentException("Flight can not be null"); // Null trip is not accepted.
        }
        if(trip instanceof FlightTrip flightTrip){
            this.flight = flightTrip;
        }
        else{
            throw new IllegalArgumentException("Trip typte must be FlightTrip"); // An exception is thrown if a trip of the wrong type is assigned.
        }
    }

}
