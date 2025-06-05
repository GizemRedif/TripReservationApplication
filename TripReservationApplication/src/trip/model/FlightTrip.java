package trip.model;
import user.model.Passenger;
import vehicle.model.Plane;
import java.time.LocalDateTime;

public class FlightTrip extends Trip{
    Plane plane;    
    
    public FlightTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalDateTime arrivalDate,
                        double fare, Passenger passengers, Seat seats, Plane plane){
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare, passengers, seats);
        this.plane=plane;
    }
    
    public FlightTrip(FlightTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getArrivalDate(),
                other.getFare(), new Passenger(other.getPassengers()), new Seat(other.getSeats()));
        this.plane=new Plane(other.plane);
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
}
