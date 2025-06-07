package reservation.model;

import vehicle.model.Seat;
import trip.model.FlightTrip;
import user.model.Passenger;

public class FlightReservation extends Reservation {
    private FlightTrip flight;
    
    public FlightReservation(Passenger passenger, double fare, Seat seat,FlightTrip flight){
        super(passenger,fare,seat);
        this.flight = flight;
    }

    public FlightTrip getFlightTrip() {
        if(flight !=null){
            return new FlightTrip(flight);
        }
        else{
            return null;
        }
    }
    
    public void setFlightTrip(FlightTrip flight){
        if(flight !=null){
            this.flight= new FlightTrip(flight);
        }
        else{
            this.flight=null;
        }
    }
   
    @Override
    public Reservation copy() {
        return new FlightReservation(this.getPassenger(), this.getFare(), this.getSeat(), this.getFlightTrip());
    }
  
    
   
    
}
