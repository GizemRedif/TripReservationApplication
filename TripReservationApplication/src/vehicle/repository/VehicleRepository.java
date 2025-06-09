package vehicle.repository;

import vehicle.model.Vehicle;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    private static VehicleRepository instance;
    private Map<String,Vehicle> vehiclesWithIdentifier;

    private VehicleRepository() {
        this.vehiclesWithIdentifier = new HashMap();
    }

    public static VehicleRepository getInstance() {
        if (instance == null) {
            instance = new VehicleRepository();
        }
        return instance;
    }
    
    /*add a new vehicle*/
    public void addVehicle(Vehicle vehicle){
        vehiclesWithIdentifier.put(vehicle.getIdentifier(),vehicle);
    }
    
    public boolean deleteVehicle(String identifier){
        vehiclesWithIdentifier.remove(identifier);
        return true;
    }
    
    public Vehicle getVehicleByIdentifierNumber(String identifier) {
        return vehiclesWithIdentifier.get(identifier);
    }
}
