/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation.factory;
import user.model.Passenger;
import reservation.model.Reservation;
import vehicle.model.Seat;
/**
 *
 * @author hp
 */
public abstract class ReservationFactory<T> {
    public abstract Reservation createReservation(Passenger passenger, int fare, Seat seat, T info);
}
