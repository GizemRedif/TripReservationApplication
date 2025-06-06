package trip.service;

import java.time.LocalDateTime;
import java.util.List;
import trip.model.Trip;
import trip.repository.TripRepository;

public class TripService {

private final TripRepository tripRepository = TripRepository.getInstance();

    public boolean createTrip(Trip trip) {
        if (trip == null){return false;}
        
        checkTripInfo(trip);
        tripRepository.addTrip(trip);
        return true;
    }
    
    public boolean cancelTrip(Trip trip){
        return tripRepository.cancelTrip(trip);
    }
    
    public boolean updateTrip(Trip updateTrip){
        if (updateTrip.getDepartureDate() == null) {
            throw new IllegalArgumentException("Invalid Departure Date");
        }
        List<Trip> allTrips = tripRepository.getAllTrips();
        for (Trip trip : allTrips) {
            if (trip.equals(updateTrip)) { 
                checkTripInfo(updateTrip);

                trip.setDepartureStation(updateTrip.getDepartureStation());
                trip.setArrivalStation(updateTrip.getArrivalStation());
                trip.setDepartureDate(updateTrip.getDepartureDate());
                trip.setArrivalDate(updateTrip.getArrivalDate());
                trip.setFare(updateTrip.getFare());
                trip.setPassengers(updateTrip.getPassengers());
                trip.setSeats(updateTrip.getSeats());

                return true;
            }
        }
        System.out.println("Trip not found.");
        return false;
    }
    private void checkTripInfo(Trip trip){
        
        if ( trip.getDepartureDate().isBefore(LocalDateTime.now()) || trip.getArrivalDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("A trip can not be created for a past date");
        }
        
        if (trip.getArrivalDate().isBefore(trip.getDepartureDate())){
            throw new IllegalArgumentException("Arrival date can not be before the departure date");
        }
        
        if (trip.getFare() <= 0){
            throw new IllegalArgumentException("Fare can't be negative or zero");
        }
        
        if (trip.getArrivalStation().equalsIgnoreCase(trip.getDepartureStation())) {
            throw new IllegalArgumentException("Departure and arrival stations cannot be the same.");
        }

    }
    
}
