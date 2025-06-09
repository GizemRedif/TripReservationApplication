package vehicle.model;

import seat.Seat;

import java.util.List;
import java.util.ArrayList;
import seat.BusSeat;

public class Bus extends Vehicle {
    private String plate;
    private final List<Seat> seatList = createDefaultSeats();
    public Bus(int capacity, String plate) {
        super(capacity);
        this.plate=plate;
    }
    
    public Bus(Bus other){
        super(other.getCapacity());
        this.plate=other.plate;
    }
    
    private static List<Seat> createDefaultSeats() {
        List<Seat> list = new ArrayList<>();
        // 45 koltuklu 2+1 oturma düzeni
        for (int i = 1; i <= 45; i++) {
            list.add(new BusSeat(i+"",i%3 == 0));
        }
        return list;
    }
    
    @Override
    public String getIdentifier() {
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
    public Bus clone(){
        return new Bus(capacity , plate);
    }
    
    @Override
    public List<Seat> getSeatList(){
        return seatList;
    }
}
