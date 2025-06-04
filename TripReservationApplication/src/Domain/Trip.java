package Domain;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public abstract class Trip {
    String departureStation;
    String arrivalStation;
    LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
    LocalDateTime arrivalDate;
    double fare;
    List<Passenger> passengers= new ArrayList<>();
    List<Seat> seats= new ArrayList<>();
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalDateTime arrivalDate, double fare){
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
        this.departureDate=departureDate;
        this.arrivalDate=arrivalDate;
        this.fare=fare;
        this.passengers= new ArrayList<>();
        this.seats= new ArrayList<>();
    }
    
}
