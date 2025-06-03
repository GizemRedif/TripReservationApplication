package Manager;

import Domain.*;

public class AdminManager {
    private Admin admin;
    private UserManager userManager = UserManager.getInstance();
    private TripManager tripManager = TripManager.getInstance();
    private VehicleManager vehicleManager = VehicleManager.getInstance();
    
    
    public void addUser(User user){
        userManager.addUser(user);
    }
    
    public void deleteUser(User user){
        userManager.deleteUser(user);
    }
    
    public void addTrip(Trip trip){
        tripManager.addTrip(trip);
    }
    
    public void cancleTrip(Trip trip){
        tripManager.cancleTrip(trip);
    }
    
    public void changeTrip(){}/*buraya trip bilgileri verilecek*/
    
    public void addVehicle(Vehicle vehicle){
        vehicleManager.addVehicle(vehicle);
    }
    
    public void deleteVehicle(Vehicle vehicle){
        vehicleManager.deleteVehicle(vehicle);
    }
    
    public void addReservation(Passenger passenger, Trip trip, Seat seat){
        passenger.addReservaition(trip,seat);
    }
    
    public void cancelReservation(Passenger passenger, Reservation reservation){
        passenger.cancelReservaition(reservation);
    }


}
