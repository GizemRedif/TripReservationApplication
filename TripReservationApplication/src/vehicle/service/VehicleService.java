
package vehicle.service;

import dto.VehicleDTO;
import vehicle.model.Bus;
import vehicle.model.Plane;
import vehicle.model.Vehicle;
import vehicle.repository.VehicleRepository;

public class VehicleService {
    private final VehicleRepository vehicleRepository = VehicleRepository.getInstance();
    
    public boolean createVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle;
        if(vehicleDTO.getVehicleType() == Bus.class){
            vehicle= new Bus(vehicleDTO.getCapacity(), vehicleDTO.getVehicleIdentifier());
        }
        else{
            vehicle= new Plane(vehicleDTO.getCapacity(),vehicleDTO.getVehicleIdentifier());
        }
        
        vehicleRepository.addVehicle(vehicle);
        return true;
    }
    
    public boolean deleteVehicle(Vehicle vehicle){
        return vehicleRepository.deleteVehicle(vehicle);
    }
}
