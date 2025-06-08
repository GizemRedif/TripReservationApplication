package trip.model;

import vehicle.model.Seat;

import user.model.Passenger;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public abstract class Trip {
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
    private LocalTime tripTime;
    private double fare;
    private List<Passenger> passengers;

     
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalTime tripTime, double fare, List<Passenger> passengers){
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
        this.departureDate=departureDate;
        this.tripTime=tripTime;
        this.fare=fare;
        this.passengers=new ArrayList<>();
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

    public LocalTime getTripTime() {
        return tripTime;
    }

    public void setTripTime(LocalTime tripTime) {
        this.tripTime = tripTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
    
    public List<Passenger> getPassengers(){
        if(passengers != null){
            return new ArrayList<>(passengers);
        }
        else{
            return null;
        }
    }
    
    public void setPassengers(List<Passenger> passengers){
        if(passengers !=null){
            this.passengers= new ArrayList<>(passengers);
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
        protected LocalTime tripTime;
        protected double fare;
        protected List<Passenger> passengers;
        
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
        public Builder setTripTime(LocalTime tripTime){
            this.tripTime=tripTime;
            return this;
        }
        public Builder setFare(double fare){
            this.fare=fare;
            return this;
        }
        
        public abstract Trip build();
        
    }
}
