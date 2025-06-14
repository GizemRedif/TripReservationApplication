package trip.service;

import dto.TripDTO;
import java.util.List;
import dto.TripSearchCriteria;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
import trip.repository.TripRepository;
import vehicle.model.Bus;
import vehicle.model.Plane;

// The TripService class handles operations such as creating, updating, canceling, and filtering trips.
public class TripService {

// Singleton repository instance that stores and manages trips.    
private final TripRepository tripRepository = TripRepository.getInstance();

    public Trip createTrip(TripDTO tripDTO) { // Creates a new Trip object based on the given TripDTO and adds it to the repository.
        if (tripDTO == null){return null;}
        
        checkTripInfo(tripDTO); // Validates the trip information.
        Trip trip;
        
        // If the trip type is BusTrip, creates a BusTrip instance.
        if (tripDTO.getTripType()== BusTrip.class) {
           trip= new BusTrip.BusTripBuilder()
                   .setBus((Bus)tripDTO.getVehicle().clone())
                   .setArrivalStation(tripDTO.getArrivalStation())
                   .setDepartureStation(tripDTO.getDepartureStation())
                   .setDepartureDate(tripDTO.getDepartureDate())
                   .setTripTime(tripDTO.getTripTime())
                   .setFare(tripDTO.getFare())
                   .build();
           
        }
        
        // If the trip type is FlightTrip, creates a FlightTrip instance. 
        else {
            trip= new FlightTrip.FlightTripBuilder()
                   .setPlane((Plane)tripDTO.getVehicle().clone())
                   .setArrivalStation(tripDTO.getArrivalStation())
                   .setDepartureStation(tripDTO.getDepartureStation())
                   .setDepartureDate(tripDTO.getDepartureDate())
                   .setTripTime(tripDTO.getTripTime())
                   .setFare(tripDTO.getFare())
                   .build();            
        }
     
        tripRepository.addTrip(trip);
        return trip;
    }
    
    // Cancels the given trip.
    public boolean cancelTrip(Trip trip){
        return tripRepository.cancelTrip(trip);
    }
    
    // Updates an existing trip with the provided new information.
    public boolean updateTrip(Trip trip ,TripDTO newTripDTO){
        checkTripInfo(newTripDTO);

        trip.setDepartureDate(newTripDTO.getDepartureDate());
        trip.setTripTime(newTripDTO.getTripTime());
        trip.setFare(newTripDTO.getFare());
                
        return true;
    }
    
    // Helper method that validates information from the TripDTO.
    private void checkTripInfo(TripDTO tripDTO){ 
        
        if (tripDTO.getFare() <= 0){ // Fare must not be zero or negative.
            throw new IllegalArgumentException("Fare can't be negative or zero.");
        }
        
        if (tripDTO.getArrivalStation().equalsIgnoreCase(tripDTO.getDepartureStation())) { // Departure and arrival stations must not be the same.
            throw new IllegalArgumentException("Departure and arrival stations cannot be the same.");
        }

    }
    
    // Filters and returns trips based on the given criteria.
    public List<Trip> filterTrips(TripSearchCriteria criteria){
        if(criteria ==null){
            throw new IllegalArgumentException("Search criteria must not be null.");
        }
        
        if (criteria.getMinFare() < 0){
            throw new IllegalArgumentException("Minimum fare cannot be negative.");
        }

        if (criteria.getMaxFare() < 0){
            throw new IllegalArgumentException("Maximum fare cannot be negative.");
        }
        
        if(criteria.getMinFare() > criteria.getMaxFare()){
            throw new IllegalArgumentException("Minimum fare cannot be greater than maximum fare.");
        }
        
        if(criteria.getDepartureStation() != null && criteria.getArrivalStation() !=null && criteria.getDepartureStation().equals(criteria.getArrivalStation())){
            throw new IllegalArgumentException("Departure and arrival stations cannot be the same.");
        }
        
    return tripRepository.search(criteria);
    }
}
