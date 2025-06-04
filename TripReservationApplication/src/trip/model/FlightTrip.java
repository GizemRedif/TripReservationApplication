package trip.model;
import vehicle.model.Plane;
import java.time.LocalDateTime;

public class FlightTrip extends Trip{
    Plane plane;    
    
    public FlightTrip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalDateTime arrivalDate, double fare, Plane plane){
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare);
        this.plane=plane;
    }
}
