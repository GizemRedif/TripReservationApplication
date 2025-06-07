package vehicle.model;

public abstract class Vehicle {
    private int capacity;
    
    public Vehicle(int capacity){
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
}
