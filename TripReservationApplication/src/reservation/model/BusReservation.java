package reservation.model;

import seat.BusSeat;
import seat.Seat;
import trip.model.BusTrip;
import trip.model.Trip;
import user.model.Passenger;

public class BusReservation extends Reservation {
    
    private BusSeat seat;
    private BusTrip busTrip;
    
    public BusReservation(Passenger passenger, double fare, BusSeat seat,BusTrip busTrip){
        super(passenger,fare);
        this.busTrip = busTrip;
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        if(seat instanceof BusSeat busSeat){
            this.seat = busSeat;
        }
        else{
            throw new IllegalArgumentException("seat typte must be BusSeat");
        }
    }
    
    @Override
    public BusTrip getTrip(){
        if(busTrip !=null){
            return new BusTrip(busTrip);
        }
        else{
            return null;
        }
    }
    
    @Override
    public void setTrip(Trip trip){
        if(trip == null){
            throw new IllegalArgumentException("busTrip can not be null");
        }
        if(trip instanceof BusTrip busTrip ){
            this.busTrip= new BusTrip(busTrip);
        }
        else{
            throw new IllegalArgumentException("Trip typte must be BusTrip");
        }
    }

//    @Override
//    public Reservation copy() {
//        return new BusReservation(this.getPassenger(), this.getFare(), this.getSeat(), this.getBusTrip());
//    }

   
    
    
}
