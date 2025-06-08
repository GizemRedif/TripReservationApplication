package reservation.model;

import trip.model.Trip;
import user.model.Passenger;
import vehicle.model.Seat;

public abstract class Reservation {
    private Passenger passenger;
    private double fare;
    
    public Reservation(Passenger passenger, double fare) {
        this.passenger = passenger;
        this.fare = fare;
    }
    
    public Passenger getPassenger(){
        if(passenger != null){
            return new Passenger(passenger);
        }
        else{
            return null;
        }
    }
    
    public void setPassenger(Passenger passengers){
        if(passengers !=null){
            this.passenger= new Passenger(passengers);
        }
        else{
            this.passenger=null;
        }
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
    
    public abstract void setSeat(Seat seat);
    public abstract Seat getSeat();
    
    public abstract void setTrip(Trip trip);
    public abstract Trip getTrip();
    
    
    

//    public abstract Reservation copy();
}
