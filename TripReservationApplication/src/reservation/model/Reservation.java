package reservation.model;

import trip.model.Seat;
import user.model.Passenger;

public abstract class Reservation {
    Passenger passenger;
    int fare;
    Seat seat;
    
    public Reservation(Passenger passenger, int fare, Seat seat) {
        this.passenger = passenger;
        this.fare = fare;
        this.seat = seat;
    }
    public abstract void reserve(); // Factory'de bunu kullanabiliriz
    
}
