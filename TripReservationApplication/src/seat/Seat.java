package seat;

// Enables the creation of cloneable objects by implementing the Cloneable interface.
public abstract class Seat implements Cloneable {
    private String seatNumber; // Stores the seat number information.
    private boolean isBooked; // Indicates whether the seat is booked or not.
    
    
    
    public Seat (String seatNumber){
        this.seatNumber=seatNumber;
        this.isBooked= false;
        
    }
    
    public Seat(Seat other){
        this.seatNumber= other.seatNumber;
        this.isBooked= other.isBooked;
    }
  
    @Override
    public abstract Seat clone();


    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}

