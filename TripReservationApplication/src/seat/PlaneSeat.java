package seat;

public class PlaneSeat extends Seat {
    private boolean isWindowSide; // Indicates whether the seat is by the window.
    private boolean isBussinessClass; // Indicates whether the seat is a business class seat.
    
    public PlaneSeat (String seatNumber, boolean isWindowSide, boolean isBussinessClass){
        super(seatNumber);
        super.setIsBooked(false);
        this.isWindowSide = isWindowSide;
        this.isBussinessClass = isBussinessClass;
    }
    
    public PlaneSeat(PlaneSeat other){
        super(other.getSeatNumber());
        super.setIsBooked(other.getIsBooked());
        this.isWindowSide = other.isWindowSide;
        this.isBussinessClass = other.isBussinessClass;
    }
    
    @Override
    public PlaneSeat clone() { // Creates a copy of the PlaneSeat object.
        PlaneSeat cloned = new PlaneSeat(super.getSeatNumber(), isWindowSide, isBussinessClass);
        cloned.setIsBooked(super.getIsBooked());
        return cloned;
    }

    public boolean isIsWindowSide() {
        return isWindowSide;
    }

    public void setIsWindowSide(boolean isWindowSide) {
        this.isWindowSide = isWindowSide;
    }

    public boolean isIsBussinessClass() {
        return isBussinessClass;
    }

    public void setIsBussinessClass(boolean isBussinessClass) {
        this.isBussinessClass = isBussinessClass;
    }

}
