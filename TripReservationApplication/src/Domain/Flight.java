package Domain;
import java.time.LocalDateTime;

public class Flight extends Trip{
    Plane plane;    
    
    public Flight(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalDateTime arrivalDate, double fare, Plane plane){
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare);
        this.plane=plane;
    }
}
