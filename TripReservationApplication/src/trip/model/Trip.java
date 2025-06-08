package trip.model;

import seat.Seat;

import reservation.model.Reservation;
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
    private List<Reservation> reservations;

     
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalTime tripTime, double fare){
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
        this.departureDate=departureDate;
        this.tripTime=tripTime;
        this.fare=fare;
        this.reservations=new ArrayList<>();
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
    
    public List<Reservation> getReservations(){
        if(reservations != null){
            return new ArrayList<>(reservations);
        }
        else{
            return null;
        }
    }
    
    public void setReservaitons(List<Reservation> reservations){
        if(reservations !=null){
            this.reservations= new ArrayList<>(reservations);
        }
        else{
            this.reservations=null;
        }
    }
    
     
    //--------------------------Builder Class------------------
    
    public static abstract class Builder{
        protected String departureStation;
        protected String arrivalStation;
        protected LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
        protected LocalTime tripTime;
        protected double fare;
                
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
