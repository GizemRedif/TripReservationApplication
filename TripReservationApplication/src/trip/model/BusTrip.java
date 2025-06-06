package trip.model;

import vehicle.model.Seat;
import user.model.Passenger;
import vehicle.model.Bus;
import java.time.LocalDateTime;


public class BusTrip extends Trip{
    Bus bus;

    public BusTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalDateTime arrivalDate,
                    double fare, Passenger passengers, Seat seats, Bus bus) {
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare, passengers);
        this.bus=bus;
        this.seats=bus.copySeatList();
    }
    
    public BusTrip(BusTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getArrivalDate(),
                other.getFare(), new Passenger(other.getPassengers()));
        this.bus=new Bus(other.bus);
        this.seats=other.bus.copySeatList();
    }
    
    public BusTrip(BusTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.arrivalDate, builder.fare, builder.passengers);
        this.bus = builder.bus;
        this.seats=builder.bus.copySeatList();
    }
    
    public Bus getBus(){
        if(bus !=null){
            return new Bus(bus);
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
            this.bus=bus;
            return this;
        }
        
          @Override
        public BusTrip build() {
            return new BusTrip(this);
        }
    }

}
    

    
   
        
    
    

