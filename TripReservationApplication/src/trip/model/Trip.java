package trip.model;
import user.model.Passenger;
import java.time.LocalDateTime;

public abstract class Trip {
    String departureStation;
    String arrivalStation;
    LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
    LocalDateTime arrivalDate;
    double fare;
    Passenger passengers;
    Seat seats;
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalDateTime arrivalDate, double fare, Passenger passengers, Seat seats){
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
        this.departureDate=departureDate;
        this.arrivalDate=arrivalDate;
        this.fare=fare;
        this.passengers= passengers;
        this.seats= seats;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
    
    public Passenger getPassengers(){
        if(passengers != null){
            return new Passenger(passengers);
        }
        else{
            return null;
        }
    }
    
    public void setPassengers(Passenger passengers){
        if(passengers !=null){
            this.passengers= new Passenger(passengers);
        }
        else{
            this.passengers=null;
        }
    }
    
    public Seat getSeats(){
        if(seats !=null){
            return new Seat(seats);
        }
        else{
            return null;
        }
    }
    
    public void setSeats(Seat seats){
        if(seats !=null){
            this.seats= new Seat(seats);
        }
        else{
            this.seats=null;
        }
    }
}
