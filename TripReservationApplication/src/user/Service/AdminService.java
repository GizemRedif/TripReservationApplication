package user.Service;

import dto.TripDTO;
import dto.UserDTO;
import trip.model.Trip;
import user.model.User;
import trip.service.TripService;

public class AdminService {
    private final UserService userService = new UserService();
     private final TripService tripService = new TripService();
     
     public boolean createUser(UserDTO userDTO) {
        return userService.createUser(userDTO); 
    }

    public boolean deleteUser(User user) {
        return userService.deleteUser(user);
    }

    public boolean addTrip(TripDTO tripDTO) {
        return tripService.createTrip(tripDTO); 
    }

    public boolean deleteTrip(Trip trip) {
        return tripService.cancelTrip(trip);
    }
    
    public boolean updateTrip(Trip trip, TripDTO tripDTO){
        return tripService.updateTrip(trip, tripDTO);
    }

}
