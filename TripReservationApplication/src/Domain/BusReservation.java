package Domain;

public class BusReservation extends Reservation {
    BusTrip busTrip;
    
    public BusReservation(Passenger passenger, int fare, Seat seat,BusTrip busTrip){
        super(passenger,fare,seat);
        this.busTrip = busTrip;
    }
    @Override
    public void reserve() {
        System.out.println("Otobüs rezervasyonu yapıldı: " + passenger + ", sefer: " + busTrip);
    }
}
