package trip.repository;

import trip.model.Trip;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import dto.TripSearchCriteria;

public class TripRepository {
    private static TripRepository instance;     //Singelton pattern
    private final List<Trip> tripList;          //List of all trips

    private TripRepository() {
        this.tripList = new ArrayList<>();
    }

    public static TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }
    
    public List<Trip> getAllTrips(){
        return new ArrayList<>(tripList);
    }
    
    //add a new trip
    public void addTrip(Trip trip){
        if(tripList.contains(trip)){
            throw new IllegalArgumentException("this trip already exists");
        }
        tripList.add(trip);
    }
    
    public boolean cancelTrip(Trip trip){
        return tripList.remove(trip);
    }

    public List<Trip> search(TripSearchCriteria criteria) {
        return tripList.stream()

            .filter(t -> criteria.getTripType() == null || criteria.getTripType().isInstance(t))

            .filter(t -> criteria.getDepartureStation() == null || 
                         t.getDepartureStation().equalsIgnoreCase(criteria.getDepartureStation()))
            .filter(t -> criteria.getArrivalStation() == null || 
                         t.getArrivalStation().equalsIgnoreCase(criteria.getArrivalStation()))

            .filter(t -> criteria.getDepartureDate() == null || 
                         t.getDepartureDate().isAfter(criteria.getDepartureDate()))

            .filter(t -> t.getFare() >= criteria.getMinFare())
            .filter(t -> t.getFare() <= criteria.getMaxFare())

            .collect(Collectors.toList());
    }

}
