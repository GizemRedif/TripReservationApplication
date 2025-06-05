package trip.service;

import java.time.LocalDateTime;
import trip.model.Trip;
import trip.repository.TripRepository;

public class TripService {

private final TripRepository tripRepository = TripRepository.getInstance();

    public boolean createTrip(Trip trip) {
        if (trip == null){return false;}
        if (trip.getDepartureDate() == null) {
            throw new IllegalArgumentException("Invalid Departure Date");
        
        }
        if ( trip.getDepartureDate().isBefore(LocalDateTime.now()) || trip.getArrivalDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("A trip can not be created for a past date");
        }
        
        if (trip.getArrivalDate().isBefore(trip.getDepartureDate())){
            throw new IllegalArgumentException("Arrival date can not be before the departure date");
        }
        if (trip.getFare() <= 0){
            throw new IllegalArgumentException("Fare can't be negative or zero");
        }
        
        if (trip.getArrivalStation().equals(trip.getArrivalStation())){
            throw new IllegalArgumentException("destination and arrival stations can not be same");
        }
        
        
        tripRepository.addTrip(trip);
        return true;
    }
    
    public boolean cancelTrip(Trip trip){
        return tripRepository.cancelTrip(trip);
    }
    
    
}
