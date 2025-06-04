package trip.model;

public class Seat {
    int seatNumber;
    boolean isBooked;
    
    public Seat (int seatNumber, boolean isBooked){
        this.seatNumber=seatNumber;
        this.isBooked= isBooked;
    }
    
    public boolean equals(Object obj){
        if(this==obj){return true;}
        if(obj==null || getClass() != obj.getClass()) {return false;}
        Seat other= (Seat) obj;
        return this.seatNumber== other.seatNumber;
    }
    
    public int hashCode(){
        return Integer.hashCode(seatNumber);
    }
}

