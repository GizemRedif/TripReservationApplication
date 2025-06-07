package trip.model;

import vehicle.model.Seat;
import user.model.Passenger;
import vehicle.model.Plane;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FlightTrip extends Trip{
    private Plane plane;
    
    public FlightTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalTime tripTime,
                        double fare, List<Passenger> passengers, Plane plane){
        super(departureStation, arrivalStation, departureDate, tripTime, fare, passengers);
        this.plane=plane;
        this.seats=plane.copySeatList();
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getTripTime(),
                other.getFare(), new ArrayList<Passenger>(other.getPassengers()));
        this.plane=new Plane(other.plane);
        if(plane != null){
            this.seats=other.plane.copySeatList();
        }
    }
      
    public FlightTrip(FlightTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.tripTime, builder.fare, builder.passengers);
        this.plane = builder.plane;
        if(plane != null){
        this.seats=builder.plane.copySeatList();
        }
    }
    
    public Plane getPlane(){
        if(plane !=null){
            return new Plane(plane);
        }
        else{
            return null;
        }
    }
    
    public void setPlane(Plane plane){
        if(plane != null){
            this.plane= new Plane(plane);
        }
        else{
            this.plane=null;
        }
    }
    
    //--------------------------Builder Class----------------
    
    public static class FlightTripBuilder extends Builder{  
        private Plane plane;
        
        public FlightTripBuilder setPlane(Plane plane){
            this.plane=plane;
            return this;
        }

        @Override
        public FlightTrip build() {
            return new FlightTrip(this);
        }

    }
}
