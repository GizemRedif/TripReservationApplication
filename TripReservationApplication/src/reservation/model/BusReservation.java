package reservation.model;

import seat.BusSeat;
import vehicle.model.Seat;
import trip.model.BusTrip;
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
    
    
    public BusTrip getBusTrip(){
        if(busTrip !=null){
            return new BusTrip(busTrip);
        }
        else{
            return null;
        }
    }
    
    public void setBusTrip(BusTrip busTrip){
        if(busTrip !=null){
            this.busTrip= new BusTrip(busTrip);
        }
        else{
            this.busTrip=null;
        }
    }

//    @Override
//    public Reservation copy() {
//        return new BusReservation(this.getPassenger(), this.getFare(), this.getSeat(), this.getBusTrip());
//    }

   
    
    
}
