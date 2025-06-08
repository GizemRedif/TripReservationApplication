package reservation.model;

import seat.PlaneSeat;
import vehicle.model.Seat;
import trip.model.FlightTrip;
import user.model.Passenger;

public class FlightReservation extends Reservation {
    
    private PlaneSeat seat;
    private FlightTrip flight;
    
    public FlightReservation(Passenger passenger, double fare, PlaneSeat seat,FlightTrip flight){
        super(passenger,fare);
        this.flight = flight;
        this.seat = seat;
    }
    
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        if(seat instanceof PlaneSeat PlaneSeat){
            this.seat = PlaneSeat;
        }
        else{
            throw new IllegalArgumentException("seat typte must be BusSeat");
        }
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
   
//    @Override
//    public Reservation copy() {
//        return new FlightReservation(this.getPassenger(), this.getFare(), this.getSeat(), this.getFlightTrip());
//    }
  
    
   
    
}
