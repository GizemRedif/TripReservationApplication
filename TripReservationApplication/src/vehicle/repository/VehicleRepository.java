package vehicle.repository;

import vehicle.model.Vehicle;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import vehicle.model.Bus;
import vehicle.model.Plane;

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
    
    public List<Vehicle> getAllBuses(){
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Map.Entry<String , Vehicle> vehicle : vehiclesWithIdentifier.entrySet()){
            if(vehicle.getValue() instanceof Bus){
                vehicleList.add(vehicle.getValue());
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getAllPlanes(){
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Map.Entry<String , Vehicle> vehicle : vehiclesWithIdentifier.entrySet()){
            if(vehicle.getValue() instanceof Plane){
                vehicleList.add(vehicle.getValue());
            }   
        }
        return vehicleList;
    }
}
