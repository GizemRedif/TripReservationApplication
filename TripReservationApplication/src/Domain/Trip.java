
package Domain;

import Domain.Passenger;
import java.time.LocalDateTime;
public abstract class Trip {
    String departureStation;
    String arrivalStation;
    LocalDateTime departureDate; /* tipi doğru mu emin değilim*/
    LocalDateTime arrivalDate; 
    Passenger passengers;
    int fare;
    Seat seats;
}
