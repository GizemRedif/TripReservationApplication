package Domain;

public class FlightReservation extends Reservation {
    Flight flight;
    
    public FlightReservation(Passenger passenger, int fare, Seat seat,Flight flight){
        super(passenger,fare,seat);
        this.flight = flight;
    }
    @Override
    public void reserve() {
        System.out.println("Uçak rezervasyonu yapıldı: " + passenger + ", uçuş: " + flight);
    }
    
}
