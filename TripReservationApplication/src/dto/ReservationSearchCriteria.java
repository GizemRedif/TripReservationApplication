package dto;

import trip.model.Trip;
import user.model.User;

// A class that holds criteria used in reservation search operations.
public class ReservationSearchCriteria {
    public static final int ALL_RESERVATIONS=0; // Constant to fetch all reservations.
    public static final int PAST_RESERVATIONS=1; // Constant to fetch past reservations.
    public static final int FUTURE_RESERVATIONS=2; // Constant to fetch future reservations.
    
    private User user; 
    private String departureStation;
    private String arrivalStation;
    private Class<? extends Trip> tripType;
    private int timeFilter=ALL_RESERVATIONS; // Time filter. By default, all reservations are listed.
    private double minFare=0.0; // Minimum fare filter.
    private double maxFare= Double.MAX_VALUE; // Maximum fare filter.

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Class<? extends Trip> getTripType() {
        return tripType;
    }

    public void setTripType(Class<? extends Trip> tripType) {
        this.tripType = tripType;
    }

    public int getTimeFilter() {
        return timeFilter;
    }

    public void setTimeFilter(int timeFilter) {
        this.timeFilter = timeFilter;
    }
    

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
}

