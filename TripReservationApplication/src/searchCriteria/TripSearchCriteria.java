package searchCriteria;

import java.time.LocalDateTime;
import trip.model.Trip;

public class TripSearchCriteria {
    private double minFare = 0.00;
    private double maxFare = Double.MAX_VALUE;
    private String departureStation;
    private String arrivalStation;
    private Class<? extends Trip> tripType;
    private LocalDateTime departureDate;
    
    public double getMinFare() {
        return minFare;
    }
    public void setMinFare(double minFare) {
        this.minFare = minFare;
    }

    public double getMaxFare() {
        return maxFare;
    }
    public void setMaxFare(double maxFare) {
        this.maxFare = maxFare;
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
    
    public Class<? extends Trip> getTripType(){
        return tripType;
    }
    public void setTripType(Class<? extends Trip> tripType){
        this.tripType=tripType;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDateTime departureTimeFrom) {
        this.departureDate = departureTimeFrom;
    }

 
}
