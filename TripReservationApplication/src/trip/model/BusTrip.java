package trip.model;

import java.time.LocalDateTime;
import vehicle.model.Bus;
import java.time.LocalTime;
import vehicle.model.Vehicle;


public class BusTrip extends Trip{
    private Bus bus; // Holds the bus object to be used in the trip.

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
    public Vehicle getVehicle(){ // Returns the vehicle (bus) to be used in the trip.
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
        public BusTrip build() { // Builds and returns the BusTrip object.
            return new BusTrip(this); 
        }
    }

}
    

    
   
        
    
    

