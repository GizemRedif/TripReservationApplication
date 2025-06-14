package dto;

import reservation.model.Reservation;
import user.model.Passenger;
import seat.Seat;
import trip.model.Trip;

// Data Transfer Object for Reservation related data.
public class ReservationDTO {

    private Passenger passenger;
    private double fare;
    private Class<? extends Reservation> reservationType;
    private Trip trip;
    private Seat seat; 

    public Passenger getPassenger() {
        return passenger;
    }

    public double getFare() {
        return fare;
    }

    public Class<? extends Reservation> getReservationType() {
        return reservationType;
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

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    
    
}

