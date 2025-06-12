package vehicle.repository;

import vehicle.model.Vehicle;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import vehicle.model.Bus;
import vehicle.model.Plane;

// A singleton repository class used to store and manage vehicles.
public class VehicleRepository {
    private static VehicleRepository instance; // Singleton instance.
    private Map<String,Vehicle> vehiclesWithIdentifier; // A vehicle database that maps each vehicle to its unique identifier.

    private VehicleRepository() { // Private constructor that ensures the repository is created only once.
        this.vehiclesWithIdentifier = new HashMap();
    }

    public static VehicleRepository getInstance() { // Returns the singleton instance creates a new one if it doesn't exist.
        if (instance == null) {
            instance = new VehicleRepository();
        }
        return instance;
    }
    
    // Adds a new vehicle and stores it using its identifier.
    public void addVehicle(Vehicle vehicle){
        vehiclesWithIdentifier.put(vehicle.getIdentifier(),vehicle);
    }
    
    // Deletes the vehicle associated with the given identifier.
    public boolean deleteVehicle(String identifier){
        vehiclesWithIdentifier.remove(identifier);
        return true;
    }
    
    // Returns the vehicle associated with the given identifier.
    public Vehicle getVehicleByIdentifierNumber(String identifier) {
        return vehiclesWithIdentifier.get(identifier);
    }
    
    public List<Vehicle> getAllBuses(){ // Returns a list of all buses in the system.
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Map.Entry<String , Vehicle> vehicle : vehiclesWithIdentifier.entrySet()){
            if(vehicle.getValue() instanceof Bus){
                vehicleList.add(vehicle.getValue());
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getAllPlanes(){ // Returns a list of all planes in the system.
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Map.Entry<String , Vehicle> vehicle : vehiclesWithIdentifier.entrySet()){
            if(vehicle.getValue() instanceof Plane){
                vehicleList.add(vehicle.getValue());
            }   
        }
        return vehicleList;
    }
}
