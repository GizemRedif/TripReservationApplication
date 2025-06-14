package vehicle.model;

import java.util.List;
import seat.Seat;

// A class that defines vehicle related properties. Implements Cloneable to allow object cloning.
// Prototype pattern is used.
public abstract class Vehicle implements Cloneable{
    
    public Vehicle clone(){ // Creates a clone of the vehicle object.
        try {
            return (Vehicle) super.clone(); // A deep copy operation is performed.
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Throws a runtime error if cloning fails.
        }
    }
    
    public abstract String getIdentifier(); // Returns the identifier specific to the vehicle.
    
    public abstract List<Seat> getSeatList(); // Returns the list of seats in the vehicle.
}

