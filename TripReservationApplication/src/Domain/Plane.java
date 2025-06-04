package Domain;

public class Plane extends Vehicle{
    int tailNumber;
    
    public Plane(int capacity, int tailNumber){
        super(capacity);
        this.tailNumber=tailNumber;
    }
}
