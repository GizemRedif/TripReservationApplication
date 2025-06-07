package reservation.factory;
import user.model.Passenger;
import reservation.model.Reservation;
import vehicle.model.Seat;

public abstract class ReservationFactory<T> {
    public abstract Reservation createReservation(Passenger passenger, int fare, Seat seat, T info);
}
