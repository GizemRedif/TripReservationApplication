package reservation.model;

import vehicle.model.Seat;
import trip.model.BusTrip;
import user.model.Passenger;

public class BusReservation extends Reservation {
    private BusTrip busTrip;
    
    public BusReservation(Passenger passenger, double fare, Seat seat,BusTrip busTrip){
        super(passenger,fare,seat);
        this.busTrip = busTrip;
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

    @Override
    public Reservation copy() {
        return new BusReservation(this.getPassenger(), this.getFare(), this.getSeat(), this.getBusTrip());
    }

   
    
    
}
