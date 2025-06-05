package trip.model;
import user.model.Passenger;
import vehicle.model.Bus;
import java.time.LocalDateTime;

public class BusTrip extends Trip{
    Bus bus;

    public BusTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalDateTime arrivalDate,
                    double fare, Passenger passengers, Seat seats, Bus bus) {
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare, passengers, seats);
        this.bus=bus;
    }
    
    public BusTrip(BusTrip other){
        super(other.getDepartureStation(), other.getArrivalStation(), other.getDepartureDate(), other.getArrivalDate(),
                other.getFare(), new Passenger(other.getPassengers()), new Seat(other.getSeats()));
        this.bus=new Bus(other.bus);
    }
    
    public Bus getBus(){
        if(bus !=null){
            return new Bus(bus);
        }
        else{
            return null;
        }
    }
    
    public void setBus(Bus bus){
        if(bus != null){
            this.bus = new Bus(bus);
        }
        else{
            this.bus=null;
        }
    }
}
