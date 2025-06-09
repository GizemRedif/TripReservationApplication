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

public class TripService {

private final TripRepository tripRepository = TripRepository.getInstance();

    public boolean createTrip(TripDTO tripDTO) {
        if (tripDTO == null){return false;}
        
        checkTripInfo(tripDTO);
        Trip trip;
        if (tripDTO.getTripType()== BusTrip.class) {
           trip= new BusTrip.BusTripBuilder()
                   .setArrivalStation(tripDTO.getArrivalStation())
                   .setDepartureStation(tripDTO.getDepartureStation())
                   .setDepartureDate(tripDTO.getDepartureDate())
                   .setTripTime(tripDTO.getTripTime())
                   .setFare(tripDTO.getFare())
                   .build();
           
        }
        else {
            trip= new FlightTrip.FlightTripBuilder()
                   .setArrivalStation(tripDTO.getArrivalStation())
                   .setDepartureStation(tripDTO.getDepartureStation())
                   .setDepartureDate(tripDTO.getDepartureDate())
                   .setTripTime(tripDTO.getTripTime())
                   .setFare(tripDTO.getFare())
                   .build();            
        }
     
        tripRepository.addTrip(trip);
        return true;
    }
    
    public boolean cancelTrip(Trip trip){
        return tripRepository.cancelTrip(trip);
    }
    
    public boolean updateTrip(Trip trip ,TripDTO newTripDTO){
        List<Trip> allTrips = tripRepository.getAllTrips();
        for (Trip t : allTrips) {
            if (t.equals(trip)) { 
                checkTripInfo(newTripDTO);

                trip.setDepartureStation(newTripDTO.getDepartureStation());
                trip.setArrivalStation(newTripDTO.getArrivalStation());
                trip.setDepartureDate(newTripDTO.getDepartureDate());
                trip.setTripTime(newTripDTO.getTripTime());
                trip.setFare(newTripDTO.getFare());
                

                return true;
            }
        }
        System.out.println("Trip not found.");
        return false;
    }
    private void checkTripInfo(TripDTO tripDTO){ 
        if(tripDTO.getTripType()==BusTrip.class){ //burdaki kontroller gereksiz olabilir.
            if(tripDTO.getVehicleType() != Bus.class){
                throw new IllegalArgumentException("Trip type is Bus, but vehicle type is not Bus.class.");
            }
        }
        else{
            if(tripDTO.getVehicleType() != Plane.class){
                throw new IllegalArgumentException("Trip type is Plane, but vehicle type is not Plane.class.");
            }
        }
        
        if (tripDTO.getFare() <= 0){
            throw new IllegalArgumentException("Fare can't be negative or zero.");
        }
        
        if (tripDTO.getArrivalStation().equalsIgnoreCase(tripDTO.getDepartureStation())) {
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
