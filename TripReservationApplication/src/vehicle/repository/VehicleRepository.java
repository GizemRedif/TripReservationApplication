package vehicle.repository;

import vehicle.model.Vehicle;

import java.util.List;
import java.util.ArrayList;

public class VehicleRepository {
    private static VehicleRepository instance;
    private List<Vehicle> vehicleList;

    private VehicleRepository() {
        this.vehicleList = new ArrayList<>();
    }

    public static VehicleRepository getInstance() {
        if (instance == null) {
            instance = new VehicleRepository();
        }
        return instance;
    }
    
    /*add a new vehicle*/
    public void addVehicle(Vehicle vehicle){
        vehicleList.add(vehicle);
    }
    
    public boolean deleteVehicle(Vehicle user){
        return vehicleList.remove(user);
    }
}
