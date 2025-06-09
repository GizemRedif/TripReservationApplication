package trip.model;

import java.time.LocalDateTime;
import seat.Seat;
import user.model.Passenger;
import vehicle.model.Bus;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import vehicle.model.Vehicle;


public class BusTrip extends Trip{
    private Bus bus;

    public BusTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalTime tripTime,
                        double fare,Bus bus) {
        super(departureStation, arrivalStation, departureDate, tripTime, fare);
        this.bus=bus.clone();
        
    }
    
    public BusTrip(BusTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getTripTime(),
                other.getFare());
        super.setReservaitons(other.getReservations());
        this.bus=(Bus)other.getVehicle().clone();
        
    }
    
    public BusTrip(BusTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.tripTime, builder.fare);
        this.bus = builder.bus;
       
    }
    
    @Override
    public Vehicle getVehicle(){
        if(bus !=null){
            return bus;
        }
        else{
            return null;
        }
    }
    
    public void setBus(Bus bus){
        if(bus != null){
            this.bus = new Bus(bus);
        }
        else{
            this.bus=null;
        }
    }
    
    //---------------------Builder Class----------------------
    
    public static class BusTripBuilder extends Builder{
        private Bus bus;
        
        public BusTripBuilder setBus(Bus bus){
            this.bus=bus.clone();
            return this;
        }
        
          @Override
        public BusTrip build() {
            return new BusTrip(this);
        }
    }

}
    

    
   
        
    
    

