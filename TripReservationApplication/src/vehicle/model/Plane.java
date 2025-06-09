package vehicle.model;

import seat.Seat;

import java.util.List;
import java.util.ArrayList;
import seat.PlaneSeat;

public class Plane extends Vehicle{
    private String tailNumber;
    private List<Seat> seatList = createDefaultSeats();
    
    public Plane(int capacity, String tailNumber){
        super(capacity);
        this.tailNumber=tailNumber;
    }
    
    public Plane(Plane other){
       super(other.getCapacity());
       this.tailNumber=other.tailNumber;
    }
    
    private static List<Seat> createDefaultSeats() {
        List<Seat> list = new ArrayList<>();
        // 150  seat first 5 lines are bussiness the rest echonomy
        
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'};
        
        for (int i = 1; i <= 25; i++) {
            for (char col : columns) {
                String seatNumber = i + "" + col; // örnek: 1
                boolean isWindowSide = (col == 'A' || col == 'F');
                boolean isBusinessClass = i <= 5;

                list.add(new PlaneSeat(seatNumber, isWindowSide, isBusinessClass));

                
            }
        }
        
        return list;
    }
    
    @Override
    public String getIdentifier() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }
    
//    public List<Seat> copySeatList(){
//        if(seatList == null){return null;}
//        
//        List<Seat> copySeat = new ArrayList<>();
//        for(Seat seat : seatList){
//            copySeat.add(seat.clone());
//        }
//        return copySeat;
//    }
    
    @Override
    public List<Seat> getSeatList(){
        return seatList;
    }
    
    @Override
    public Plane clone(){
        return new Plane(capacity , tailNumber);
    }
    
}
