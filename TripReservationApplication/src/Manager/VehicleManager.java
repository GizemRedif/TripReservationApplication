package Manager;

import Domain.Vehicle;

import java.util.List;
import java.util.ArrayList;

public class VehicleManager {
    
    private static VehicleManager instance;
    private List<Vehicle> vehicles;
    
    private VehicleManager() {}
    
    public static VehicleManager getInstance() {
        if (instance == null) {
            instance = new VehicleManager();
        }
        return instance;
    }
    
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);        
    }
    
    public void deleteVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }
    
    
    
}
