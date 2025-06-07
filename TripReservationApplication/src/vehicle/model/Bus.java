package vehicle.model;

import vehicle.model.Seat;

import java.util.List;
import java.util.ArrayList;

public class Bus extends Vehicle {
    private String plate;
    private List<Seat> seatList = createDefaultSeats();
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
            list.add(new Seat(i,false));
        }
        return list;
    }
     
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    
    public List<Seat> copySeatList(){
        if(seatList == null){return null;}
        
        List<Seat> copySeat = new ArrayList<>();
        for(Seat seat : seatList){
            copySeat.add(seat.clone());
        }
        return copySeat;
    }
}
