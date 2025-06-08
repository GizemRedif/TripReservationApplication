package vehicle.model;

public class Seat {
    private int seatNumber;
    private boolean isBooked;
    private boolean isWindowSide;
    private boolean isSingle;
    
    public Seat (int seatNumber, boolean isBooked, boolean isWindowSide, boolean isSingle){
        this.seatNumber=seatNumber;
        this.isBooked= isBooked;
        this.isWindowSide = isWindowSide;
        this.isSingle = isSingle;
        
    }
    
    public Seat(Seat other){
        this.seatNumber= other.seatNumber;
        this.isBooked= other.isBooked;
        this.isWindowSide = other.isWindowSide;
        this.isSingle = other.isSingle;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){return true;}
        if(obj==null || getClass() != obj.getClass()) {return false;}
        Seat other= (Seat) obj;
        return this.seatNumber== other.seatNumber && this.isBooked == other.isBooked && this.isWindowSide == other.isWindowSide && this.isSingle == other.isSingle;
    }
    
    @Override
    public Seat clone() {
        Seat copy = new Seat(this.seatNumber , this.isBooked , this.isWindowSide , this.isSingle);
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

    public boolean getisBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean isIsWindowSide() {
        return isWindowSide;
    }

    public void setIsWindowSide(boolean isWindowSide) {
        this.isWindowSide = isWindowSide;
    }

    public boolean isIsSingle() {
        return isSingle;
    }

    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }
    
    
}

