package user.Service;

import trip.model.Trip;
import user.model.User;
import trip.service.TripService;
import user.Service.UserService;

public class AdminService {
    private final UserService userService = new UserService();
     private final TripService tripService = new TripService();
     
     public boolean createUser(User user) {
        return userService.createUser(user); 
    }

    public boolean deleteUser(User user) {
        return userService.deleteUser(user);
    }

    public boolean addTrip(Trip trip) {
        return tripService.createTrip(trip); 
    }

    public boolean deleteTrip(Trip trip) {
        return tripService.cancelTrip(trip);
    }
    
    public boolean updateTrip(Trip newTrip){
        return tripService.updateTrip(newTrip);
    }

}
