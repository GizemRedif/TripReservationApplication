/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;
import Domain.Passenger;
import Domain.Reservation;
import Domain.Seat;
/**
 *
 * @author hp
 */
public abstract class ReservationFactory {
    public abstract Reservation createReservation(Passenger passenger, int fare, Seat seat, String info);
}
