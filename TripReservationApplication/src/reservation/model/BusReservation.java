package reservation.model;

import seat.BusSeat;
import seat.Seat;
import trip.model.BusTrip;
import trip.model.Trip;
import user.model.Passenger;

public class BusReservation extends Reservation {
    
    private BusSeat seat; // Holds the bus seat information.
    private BusTrip busTrip; // Holds the bus trip information.
    
    public BusReservation(Passenger passenger, double fare, BusSeat seat,BusTrip busTrip){
        super(passenger,fare); // Passenger and fare information is taken from the superclass.
        this.busTrip = busTrip;
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    // Seat information is set. Only objects of type BusSeat are accepted.
    public void setSeat(Seat seat) {
        if(seat instanceof BusSeat busSeat){
            this.seat = busSeat;
        }
        else{
            throw new IllegalArgumentException("Seat typte must be BusSeat."); // An exception is thrown if a seat of the wrong type is assigned.
        }
    }
    
    @Override
    public BusTrip getTrip(){
        if(busTrip !=null){
            return new BusTrip(busTrip);
        }
        else{
            return null;
        }
    }
    
    // Bus trip information is set. If it is null or of the wrong type, an exception is thrown.
    @Override
    public void setTrip(Trip trip){
        if(trip == null){
            throw new IllegalArgumentException("busTrip can not be null"); // Null trip is not accepted.
        }
        if(trip instanceof BusTrip busTrip ){
            this.busTrip= new BusTrip(busTrip);
        }
        else{
            throw new IllegalArgumentException("Trip typte must be BusTrip"); // An exception is thrown if a trip of the wrong type is assigned.
        }
    }
}
