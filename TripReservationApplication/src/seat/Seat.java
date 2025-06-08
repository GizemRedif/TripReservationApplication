package seat;

public abstract class Seat implements Cloneable {
    private String seatNumber;
    private boolean isBooked;
    
    
    
    public Seat (String seatNumber){
        this.seatNumber=seatNumber;
        this.isBooked= false;
        
    }
    
    public Seat(Seat other){
        this.seatNumber= other.seatNumber;
        this.isBooked= other.isBooked;
    }
    
//    @Override
//    public boolean equals(Object obj){
//        if(this==obj){return true;}
//        if(obj==null || getClass() != obj.getClass()) {return false;}
//        Seat other= (Seat) obj;
//        return this.seatNumber== other.seatNumber && this.isBooked == other.isBooked;
//    }
    
    @Override
    public abstract Seat clone();
    
//    @Override
//    public int hashCode(){
//        return Integer.hashCode(seatNumber);
//    }

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

