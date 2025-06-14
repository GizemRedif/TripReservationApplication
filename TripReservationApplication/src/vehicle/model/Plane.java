package vehicle.model;

import seat.Seat;

import java.util.List;
import java.util.ArrayList;
import seat.PlaneSeat;

// A class that represents airplane-type vehicles.
public class Plane extends Vehicle{
    private String tailNumber; // Stores the tail number of the airplane.
    private final List<Seat> seatList = createDefaultSeats(); // Holds the seat list of the airplane. By default, 150 seats are created.
    
    public Plane(String tailNumber){
        this.tailNumber=tailNumber;
    }
    
    public Plane(Plane other){
       this.tailNumber=other.tailNumber;
    }
    
    // Creates the seat list based on the default seating layout.
    // The first 5 rows are set as business class, the rest as economy class.
    private static List<Seat> createDefaultSeats() {
        List<Seat> list = new ArrayList<>();
        
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'}; // Defines 25 rows with 6 seats (A-F) in each row.
        
        for (int i = 1; i <= 25; i++) {
            for (char col : columns) {
                String seatNumber = i + "" + col; // örnek: 1
                boolean isWindowSide = (col == 'A' || col == 'F'); // A and F are window-side seats.
                boolean isBusinessClass = i <= 5; // The first 5 rows are considered business class.

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
    public Plane clone(){ // Creates a clone of the airplane object (Prototype Pattern).
        return new Plane(tailNumber);
    }
    
}
