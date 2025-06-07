package searchCriteria;

import java.time.LocalDateTime;
import trip.model.Trip;

public class TripSearchCriteria {
    private Double minFare;
    private Double maxFare;
    private String departureStation;
    private String arrivalStation;
    private Class<? extends Trip> tripType;
    private LocalDateTime departureTime;
    
    public Double getMinFare() {
        return minFare;
    }
    public void setMinFare(Double minFare) {
        this.minFare = minFare;
    }

    public Double getMaxFare() {
        return maxFare;
    }
    public void setMaxFare(Double maxFare) {
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTimeFrom) {
        this.departureTime = departureTimeFrom;
    }

 
}
