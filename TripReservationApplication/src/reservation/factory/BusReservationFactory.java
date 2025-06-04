/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation.factory;

import reservation.model.BusReservation;
import user.model.Passenger;
import reservation.model.Reservation;
import trip.model.Seat;
import trip.model.BusTrip;

/**
 *
 * @author hp
 */
public class BusReservationFactory extends ReservationFactory <BusTrip> {
    
    public Reservation createReservation(Passenger passenger, int fare, Seat seat, BusTrip info){
        return new BusReservation(passenger, fare, seat, info);
    }
}
