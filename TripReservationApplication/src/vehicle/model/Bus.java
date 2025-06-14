package vehicle.model;

import seat.Seat;

import java.util.List;
import java.util.ArrayList;
import seat.BusSeat;

// A class that represents bus-type vehicles.
public class Bus extends Vehicle {
    private String plate; // Stores the license plate information of the bus.
    private final List<Seat> seatList = createDefaultSeats(); // Holds the seat list of the bus. Creates 45 seats by default.


    public Bus(String plate) {
        this.plate=plate;
    }
    
    public Bus(Bus other){
        this.plate=other.plate;
    }
    
    // Creates 45 seats in a default 2+1 seating layout.
    private static List<Seat> createDefaultSeats() {
        List<Seat> list = new ArrayList<>();
        
        for (int i = 1; i <= 45; i++) {
            list.add(new BusSeat(i+"",i%3 == 0)); // Every 3rd seat is marked as a single seat.
        }
        return list;
    }
    
    @Override
    public String getIdentifier() { // Returns the identifier specific to the bus (license plate).
        return plate; 
    }

    public void setPlate(String plate) {
        this.plate = plate;
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
    public Bus clone(){ // Creates a clone of the bus object (Prototype Pattern).
        return new Bus( plate);
    }
    
    @Override
    public List<Seat> getSeatList(){
        return seatList;
    }
}
