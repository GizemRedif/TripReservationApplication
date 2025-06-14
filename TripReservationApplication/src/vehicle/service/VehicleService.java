package vehicle.service;

import dto.VehicleDTO;
import java.util.List;
import vehicle.model.Bus;
import vehicle.model.Plane;
import vehicle.model.Vehicle;
import vehicle.repository.VehicleRepository;

public class VehicleService {
    private final VehicleRepository vehicleRepository = VehicleRepository.getInstance(); // The singleton instance of the repository class that manages vehicles.
    
    public boolean createVehicle(VehicleDTO vehicleDTO){ // Creates a new vehicle based on the given VehicleDTO and adds it to the repository.


        Vehicle vehicle;
        if(vehicleDTO.getVehicleType() == Bus.class){ // If the vehicle type is Bus, a Bus object is created.
            vehicle= new Bus(vehicleDTO.getCapacity(), vehicleDTO.getVehicleIdentifier());
        }
        else{ // If the vehicle type is Plane, a Plane object is created.
            vehicle= new Plane(vehicleDTO.getCapacity(),vehicleDTO.getVehicleIdentifier());
        }
        
        vehicleRepository.addVehicle(vehicle);
        return true;
    }
    
    // Deletes the vehicle associated with the given identifier.
    public boolean deleteVehicleByIdentifier(String identifier){
        return vehicleRepository.deleteVehicle(identifier);
    }
    
    // Returns the vehicle associated with the given identifier.
    public Vehicle getVehicleByIdentifier(String indentifier){
        return vehicleRepository.getVehicleByIdentifierNumber(indentifier);
    }
    
    // Returns a list of all buses in the system.
    public List<Vehicle> getAllBuses(){
        return vehicleRepository.getAllBuses();
    }
    
    // Returns a list of all planes in the system.
    public List<Vehicle> getAllPlanes(){
        return vehicleRepository.getAllPlanes();
    }
}
