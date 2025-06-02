/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import Domain.BusTrip;
import Domain.Flight;
import Domain.FlightReservation;
import Domain.Passenger;
import Domain.Reservation;
import Domain.Seat;

/**
 *
 * @author hp
 */
public class FlightReservationFactory extends ReservationFactory <Flight>{
 
    public Reservation createReservation(Passenger passenger, int fare, Seat seat, Flight info){
        return new FlightReservation(passenger, fare, seat, info);
    }
}
