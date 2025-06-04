package trip.model;
import vehicle.model.Bus;
import java.time.LocalDateTime;

public class BusTrip extends Trip{
    Bus bus;

    public BusTrip(String departureStation, String arrivalStation, LocalDateTime departureDate, LocalDateTime arrivalDate, double fare, Bus bus) {
        super(departureStation, arrivalStation, departureDate, arrivalDate, fare);
        this.bus=bus;
    }
}
