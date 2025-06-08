package vehicle.model;

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
    
}
