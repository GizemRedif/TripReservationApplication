package trip.model;

import reservation.model.Reservation;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import vehicle.model.Vehicle;

public abstract class Trip {
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureDate; 
    private LocalTime tripTime;
    private double fare;
    private List<Reservation> reservations;

     
    
    public Trip(String departureStation, String arrivalStation,LocalDateTime departureDate,LocalTime tripTime, double fare){
        this.departureStation=departureStation; // Stores the departure station informatio
        this.arrivalStation=arrivalStation; // Stores the arrival station information. 
        this.departureDate=departureDate; // Stores the departure date and time.
        this.tripTime=tripTime; // Stores the trip time.
        this.fare=fare; // Stores the fare of the trip.
        this.reservations=new ArrayList<>(); // Stores the list of reservations for this trip.
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
    
    // Adds a new reservation to the trip's list.
    public void addReservation(Reservation reservation){ 
        reservations.add(reservation);
    }
    
    // Removes the specified reservation from the trip's list.
    public void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }
    
    public abstract Vehicle getVehicle();
     
    //--------------------------Builder Class------------------
    
    // An abstract Builder class used to construct Trip objects step by step.
    public static abstract class Builder{
        protected String departureStation;
        protected String arrivalStation;
        protected LocalDateTime departureDate;
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
        
        public abstract Trip build(); // Builds and returns a Trip object.
        
    }
}
