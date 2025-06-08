package seat;



public class BusSeat extends Seat {
    private boolean isSingle;
    
    public BusSeat (String seatNumber, boolean isSingle){
        super(seatNumber);
        this.isSingle = isSingle;
    }
    
    public BusSeat (BusSeat other){
        super(other);
        this.isSingle = other.isSingle;
    }
    
    @Override
    public BusSeat clone() {
        BusSeat cloned = new BusSeat(super.getSeatNumber(), isSingle);
        cloned.setIsBooked(super.getIsBooked());
        return cloned;
    }

    public boolean isIsSingle() {
        return isSingle;
    }

    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }
    
}
