package trip.repository;

import trip.model.Trip;

import java.util.List;
import java.util.ArrayList;

public class TripRepository {
    private static TripRepository instance;
    private List<Trip> tripList;

    private TripRepository() {
        this.tripList = new ArrayList<>();
    }

    public static TripRepository getInstance() {
        if (instance == null) {
            instance = new TripRepository();
        }
        return instance;
    }
    
    /*add a new trip*/
    public void addTrip(Trip trip){
        tripList.add(trip);
    }
    
    public boolean cancelTrip(Trip trip){
        return tripList.remove(trip);
    }

}
