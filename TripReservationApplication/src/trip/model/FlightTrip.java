package trip.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import vehicle.model.Vehicle;
import vehicle.model.Plane;

public class FlightTrip extends Trip{
    private Plane plane; // Holds the plane object to be used in the trip.
    
    public FlightTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalTime tripTime,
                        double fare, Plane plane){
        super(departureStation, arrivalStation, departureDate, tripTime, fare);
        this.plane=plane.clone();
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getTripTime(),
                other.getFare());
        super.setReservaitons(other.getReservations());
        this.plane=(Plane)other.getVehicle().clone();
    }
      
    public FlightTrip(FlightTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.tripTime, builder.fare);
        this.plane = builder.plane;
    }
    
    public Vehicle getVehicle(){ // Returns the vehicle (plane) to be used in the trip.
        if(plane !=null){
            return plane;
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
        public FlightTrip build() { // Builds and returns the FlightTrip object.
            return new FlightTrip(this);
        }

    }
}
