package trip.model;

import seat.Seat;
import reservation.model.Reservation;
import vehicle.model.Plane;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FlightTrip extends Trip{
    private Plane plane;
    
    public FlightTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalTime tripTime,
                        double fare, Plane plane){
        super(departureStation, arrivalStation, departureDate, tripTime, fare);
        this.plane=plane.clone();
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getTripTime(),
                other.getFare());
        super.setReservaitons(other.getReservations());
        this.plane=other.plane.clone();
    }
      
    public FlightTrip(FlightTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.tripTime, builder.fare);
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
