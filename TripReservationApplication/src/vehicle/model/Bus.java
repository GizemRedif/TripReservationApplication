package vehicle.model;

public class Bus extends Vehicle {
     String plate;

    public Bus(int capacity, String plate) {
        super(capacity);
        this.plate=plate;
    }
    
    public Bus(Bus other){
        super(other.getCapacity());
        this.plate=other.plate;
    }
    
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
