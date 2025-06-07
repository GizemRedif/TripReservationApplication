package vehicle.model;

public class Seat {
    private int seatNumber;
    private boolean isBooked;
    
    public Seat (int seatNumber, boolean isBooked){
        this.seatNumber=seatNumber;
        this.isBooked= isBooked;
    }
    
    public Seat(Seat other){
        this.seatNumber= other.seatNumber;
        this.isBooked= other.isBooked;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){return true;}
        if(obj==null || getClass() != obj.getClass()) {return false;}
        Seat other= (Seat) obj;
        return this.seatNumber== other.seatNumber;
    }
    
    @Override
    public Seat clone() {
        Seat copy = new Seat(this.seatNumber , false);
        return copy;
    }
    
    @Override
    public int hashCode(){
        return Integer.hashCode(seatNumber);
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
    
    
}

