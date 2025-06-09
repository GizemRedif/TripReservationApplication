package dto;

import reservation.model.Reservation;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import user.model.Passenger;

public class ReservationDTO {

    private Passenger passenger;
    private double fare;
    private Class<? extends Reservation> reservationType;
    private BusTrip busTrip;
    private FlightTrip flight;

    public Passenger getPassenger() {
        return passenger;
    }

    public double getFare() {
        return fare;
    }

    public Class<? extends Reservation> getReservationType() {
        return reservationType;
    }

    public BusTrip getBusTrip() {
        return busTrip;
    }

    public FlightTrip getFlight() {
        return flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setReservationType(Class<? extends Reservation> reservationType) {
        this.reservationType = reservationType;
    }

    public void setBusTrip(BusTrip busTrip) {
        this.busTrip = busTrip;
    }

    public void setFlight(FlightTrip flight) {
        this.flight = flight;
    }
    
}

