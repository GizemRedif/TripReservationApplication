package Domain;

public abstract class Reservation {
    Passenger passenger;
    int fare;
    Seat seat;
    
    public Reservation(Passenger passenger, int fare, Seat seat){
        this.passenger=passenger;
        this.fare=fare;
        this.seat=seat;
        
    }
}
