package vehicle.model;

import java.util.List;
import seat.Seat;


public abstract class Vehicle implements Cloneable{
    protected int capacity;
    
    public Vehicle(int capacity){
        this.capacity=capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public Vehicle clone(){
        try {
            return (Vehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    public abstract List<Seat> getSeatList();
}

