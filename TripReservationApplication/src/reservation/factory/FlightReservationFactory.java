/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation.factory;

import trip.model.BusTrip;
import trip.model.FlightTrip;
import reservation.model.FlightReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import trip.model.Seat;

/**
 *
 * @author hp
 */
public class FlightReservationFactory extends ReservationFactory <FlightTrip>{
 
    public Reservation createReservation(Passenger passenger, int fare, Seat seat, FlightTrip info){
        return new FlightReservation(passenger, fare, seat, info);
    }
}
