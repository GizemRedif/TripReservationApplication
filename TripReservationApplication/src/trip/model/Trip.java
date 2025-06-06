package trip.model;

import vehicle.model.Seat;

import user.model.Passenger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public abstract class Trip {
    String departureStation;
    String arrivalStation;
    LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
    LocalDateTime arrivalDate;
    double fare;
    Passenger passengers;
    List<Seat> seats;
     
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalDateTime arrivalDate, double fare, Passenger passengers){
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
        this.departureDate=departureDate;
        this.arrivalDate=arrivalDate;
        this.fare=fare;
        this.passengers= passengers;
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
    
   
    
    //--------------------------Builder Class------------------
    
    public static abstract class Builder{
        protected String departureStation;
        protected String arrivalStation;
        protected LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
        protected LocalDateTime arrivalDate;
        protected double fare;
        protected Passenger passengers;
        protected Seat seats;
        
        public Builder setDepartureStation(String departureStation){
            this.departureStation=departureStation;
            return this;
        }
        public Builder setArrivalStation(String arrivalStation){
            this.arrivalStation=arrivalStation;
            return this;
        }
        public Builder setDepartureDate(LocalDateTime departureDate){
            this.departureDate=departureDate;
            return this;
        }
        public Builder setArrivalDate(LocalDateTime arrivalDate){
            this.arrivalDate=arrivalDate;
            return this;
        }
        public Builder setFare(double fare){
            this.fare=fare;
            return this;
        }
        public Builder setPassenengers(Passenger passengers){
            this.passengers=passengers;
            return this;
        }
        
        public abstract Trip build();
        
    }
}
