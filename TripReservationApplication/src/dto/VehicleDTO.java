package dto;

import vehicle.model.Vehicle;

// Data Transfer Object for Vehicle related data.
public class VehicleDTO {
    private String vehicleIdentifier;
    private Class<? extends Vehicle> vehicleType;

    public String getVehicleIdentifier() {
        return vehicleIdentifier;
    }

    public void setVehicleIdentifier(String vehicleIdentifier) {
        this.vehicleIdentifier = vehicleIdentifier;
    }

    public Class<? extends Vehicle> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Class<? extends Vehicle> vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    
    
}
