package vehicle.model;

public class Plane extends Vehicle{
    int tailNumber;
    
    public Plane(int capacity, int tailNumber){
        super(capacity);
        this.tailNumber=tailNumber;
    }
    
    public Plane(Plane other){
       super(other.getCapacity());
       this.tailNumber=other.tailNumber;
    }

    public int getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(int tailNumber) {
        this.tailNumber = tailNumber;
    }
    
}
