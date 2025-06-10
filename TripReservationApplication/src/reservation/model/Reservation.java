package reservation.model;

import trip.model.Trip;
import user.model.Passenger;
import seat.Seat;

//The Reservation class is an abstract class that holds information about a passenger and the reservation fare.
public abstract class Reservation {
    private Passenger passenger;
    private double fare;
    
    public Reservation(Passenger passenger, double fare) {
        this.passenger = passenger;
        this.fare = fare;
    }
    
    //Returns the passenger information. If the passenger is not null, it returns a copy of it.
    public Passenger getPassenger(){
        if(passenger != null){
            return new Passenger(passenger);
        }
        else{
            return null;
        }
    }
    
    //Sets the passenger information. If the parameter is not null, it creates a new copy of it.
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
    
    //Seat information is set.
    public abstract void setSeat(Seat seat);
    public abstract Seat getSeat();
    
    //Trip information is set.
    public abstract void setTrip(Trip trip);
    public abstract Trip getTrip();
    
}
