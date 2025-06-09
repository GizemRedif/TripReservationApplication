package trip.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import dto.TripSearchCriteria;
import trip.model.Trip;
import trip.repository.TripRepository;

public class TripService {

private final TripRepository tripRepository = TripRepository.getInstance();

    //buraya belirtilen Tripteki rezervasyon listesine verilen rezervasyonu ekleyen bir metod yazılmalı
    public boolean createTrip(Trip trip) {
        if (trip == null){return false;}
        
        checkTripInfo(trip);
        tripRepository.addTrip(trip);
        return true;
    }
    
    public boolean cancelTrip(Trip trip){
        return tripRepository.cancelTrip(trip);
    }
    
    public boolean updateTrip(Trip trip ,Trip newTrip){
        List<Trip> allTrips = tripRepository.getAllTrips();
        for (Trip t : allTrips) {
            if (t.equals(trip)) { 
                checkTripInfo(newTrip);

                trip.setDepartureStation(newTrip.getDepartureStation());
                trip.setArrivalStation(newTrip.getArrivalStation());
                trip.setDepartureDate(newTrip.getDepartureDate());
                trip.setTripTime(newTrip.getTripTime());
                trip.setFare(newTrip.getFare());
                trip.setReservaitons(newTrip.getReservations());

                return true;
            }
        }
        System.out.println("Trip not found.");
        return false;
    }
    private void checkTripInfo(Trip trip){
        
        if (trip.getFare() <= 0){
            throw new IllegalArgumentException("Fare can't be negative or zero");
        }
        
        if (trip.getArrivalStation().equalsIgnoreCase(trip.getDepartureStation())) {
            throw new IllegalArgumentException("Departure and arrival stations cannot be the same.");
        }

    }
    
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
