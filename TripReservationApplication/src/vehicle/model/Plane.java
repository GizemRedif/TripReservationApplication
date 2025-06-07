package vehicle.model;

import vehicle.model.Seat;

import java.util.List;
import java.util.ArrayList;

public class Plane extends Vehicle{
    private int tailNumber;
    private List<Seat> seatList = createDefaultSeats();
    
    public Plane(int capacity, int tailNumber){
        super(capacity);
        this.tailNumber=tailNumber;
    }
    
    public Plane(Plane other){
       super(other.getCapacity());
       this.tailNumber=other.tailNumber;
    }
    
    private static List<Seat> createDefaultSeats() {
        List<Seat> list = new ArrayList<>();
        // 45 koltuklu 2+1 oturma düzeni
        for (int i = 1; i <= 200; i++) {
            list.add(new Seat(i,false));
        }
        return list;
    }
    
    public int getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(int tailNumber) {
        this.tailNumber = tailNumber;
    }
    
    public List<Seat> copySeatList(){
        List<Seat> copySeat = new ArrayList<>();
        for(Seat seat : seatList){
            copySeat.add(seat);
        }
        return copySeat;
    }
    
}
