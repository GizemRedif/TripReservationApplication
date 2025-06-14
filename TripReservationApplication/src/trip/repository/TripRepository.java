package trip.repository;

import trip.model.Trip;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import dto.TripSearchCriteria;

// The TripRepository class is a singleton repository that manages all trips.
public class TripRepository {
    private static TripRepository instance;     // Singleton instance
    private final List<Trip> tripList;          // Stores the list of all trips registered in the system.

    // Private constructor that ensures it can only be instantiated once.
    private TripRepository() {
        this.tripList = new ArrayList<>();
    }

    // Returns the singleton instance, creates it if it hasn't been created yet.
    public static TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }
    
    public List<Trip> getAllTrips(){ // Returns a copy of all trips stored in the system.
        return new ArrayList<>(tripList);
    }
    
    // Adds a new trip, throws an error if the same trip already exists.
    public void addTrip(Trip trip){
        if(tripList.contains(trip)){
            throw new IllegalArgumentException("this trip already exists");
        }
        tripList.add(trip);
    }
    
    public boolean cancelTrip(Trip trip){ // Cancels the specified trip.
        return tripList.remove(trip);
    }

    // Filters and returns trips based on the given search criteria.
    public List<Trip> search(TripSearchCriteria criteria) {
        return tripList.stream()

             // Filters by trip type (bus/plane).
            .filter(t -> criteria.getTripType() == null || criteria.getTripType().isInstance(t))

             // Filters by departure station.
            .filter(t -> criteria.getDepartureStation() == null || 
                         t.getDepartureStation().equalsIgnoreCase(criteria.getDepartureStation()))
             
             // Filters by arrival station.
            .filter(t -> criteria.getArrivalStation() == null || 
                         t.getArrivalStation().equalsIgnoreCase(criteria.getArrivalStation()))

             // Filters by departure date.
            .filter(t -> criteria.getDepartureDate() == null || 
                         t.getDepartureDate().toLocalDate().equals(criteria.getDepartureDate().toLocalDate()))
             
            .filter(t -> t.getFare() >= criteria.getMinFare())  // Filters by minimum fare.
            .filter(t -> t.getFare() <= criteria.getMaxFare())  // Filters by maximum fare.

            .collect(Collectors.toList());
    }

}
