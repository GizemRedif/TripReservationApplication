package trip.model;

import vehicle.model.Seat;
import user.model.Passenger;
import vehicle.model.Plane;
import java.time.LocalDateTime;


public class FlightTrip extends Trip{
    Plane plane;
    
    public FlightTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalDateTime arrivalDate,
                        double fare, Passenger passengers, Seat seats, Plane plane){
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare, passengers);
        this.plane=plane;
        this.seats=plane.copySeatList();
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getArrivalDate(),
                other.getFare(), new Passenger(other.getPassengers()));
        this.plane=new Plane(other.plane);
        this.seats=other.plane.copySeatList();
    }
      
    public FlightTrip(FlightTripBuilder builder) {
        super(builder.departureStation, builder.arrivalStation, builder.departureDate,
              builder.arrivalDate, builder.fare, builder.passengers);
        this.plane = builder.plane;
        this.seats=builder.plane.copySeatList();
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
