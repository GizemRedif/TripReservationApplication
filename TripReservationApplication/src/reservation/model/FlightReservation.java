package reservation.model;

import trip.model.Seat;
import trip.model.FlightTrip;
import user.model.Passenger;

public class FlightReservation extends Reservation {
    FlightTrip flight;
    
    public FlightReservation(Passenger passenger, int fare, Seat seat,FlightTrip flight){
        super(passenger,fare,seat);
        this.flight = flight;
    }
    @Override
    public void reserve() {
        System.out.println("Uçak rezervasyonu yapıldı: " + passenger + ", uçuş: " + flight);
    }
    
}
