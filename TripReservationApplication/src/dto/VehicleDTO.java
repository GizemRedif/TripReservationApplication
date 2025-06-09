package dto;

import vehicle.model.Vehicle;

public class VehicleDTO {
    private int capacity;
    private String vehicleIdentifier;
    private Class<? extends Vehicle> vehicleType;
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

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
