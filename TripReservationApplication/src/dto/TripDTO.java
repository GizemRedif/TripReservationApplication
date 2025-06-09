
package dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import trip.model.Trip;
import vehicle.model.Vehicle;

public class TripDTO {
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureDate; 
    private LocalTime tripTime;
    private double fare;
    private Class<? extends Trip> tripType;
    Vehicle vehicle;

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

    public Class<? extends Trip> getTripType() {
        return tripType;
    }

    public void setTripType(Class<? extends Trip> tripType) {
        this.tripType = tripType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    
  
}
