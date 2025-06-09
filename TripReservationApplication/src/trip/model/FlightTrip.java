package trip.model;


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
        this.plane=plane.clone();
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getTripTime(),
                other.getFare(), new ArrayList<>(other.getPassengers()));
        this.plane=other.plane.clone();
    }
      
    public FlightTrip(FlightTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.tripTime, builder.fare, builder.passengers);
        this.plane = builder.plane;
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
            this.plane=plane.clone();
            return this;
        }

        @Override
        public FlightTrip build() {
            return new FlightTrip(this);
        }

    }
}
