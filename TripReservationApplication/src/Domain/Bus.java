package Domain;

public class Bus extends Vehicle {
     String plate;

    public Bus(int capacity, String plate) {
        super(capacity);
        this.plate=plate;
    }
}
