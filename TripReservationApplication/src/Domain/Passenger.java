package Domain;
import java.util.List;
import java.util.ArrayList;

public class Passenger extends User {
    List<Reservation> reservations = new ArrayList<>();
    String paymentInfo; /*tipi için şimdilik string yazdım*/

    public Passenger(String name, String surname, String eMail, String password, String phoneNumber, char gender, String paymentInfo) {
        super(name, surname, eMail, password, phoneNumber, gender);
        this.reservations= new ArrayList<>();
        this.paymentInfo=paymentInfo;
    }
    
    public boolean equals(Object obj){
        if(this==obj) {return true;}
        if(obj==null || getClass() != obj.getClass()){return false;}
        Passenger other= (Passenger) obj;
        return this.eMail.equals(other.eMail);
    }
    
    public int hashCode(){
        return eMail.hashCode();
    }
}
