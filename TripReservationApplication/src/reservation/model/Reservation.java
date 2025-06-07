package reservation.model;

import vehicle.model.Seat;
import user.model.Passenger;

public abstract class Reservation {
    private Passenger passenger;
    private double fare;
    private Seat seat;
    
    public Reservation(Passenger passenger, double fare, Seat seat) {
        this.passenger = passenger;
        this.fare = fare;
        this.seat = seat;
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
    
    
    public Seat getSeat(){
        if(seat !=null){
            return new Seat(seat);
        }
        else{
            return null;
        }
    }
    
    public void setSeat(Seat seats){
        if(seats !=null){
            this.seat= new Seat(seats);
        }
        else{
            this.seat=null;
        }
    }

    public abstract Reservation copy();
}
