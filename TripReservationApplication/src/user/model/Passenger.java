package user.model;
import reservation.model.Reservation;
import java.util.List;
import java.util.ArrayList;

public class Passenger extends User {
    String paymentInfo; /*tipi için şimdilik string yazdım*/

    public Passenger(String name, String surname, String eMail, String password, String phoneNumber, char gender, String paymentInfo) {
        super(name, surname, eMail, password, phoneNumber, gender);
        this.paymentInfo=paymentInfo;
    }
    
    public Passenger(Passenger other){
        super(other.getName(), other.getSurname(), other.getEmail(), other.getPassword(), other.getPhoneNumber(), other.getGender());
        this.paymentInfo=other.paymentInfo;
    }
    
    public Passenger(PassengerBuilder builder){
        super(builder.name, builder.surname, builder.eMail, builder.password, builder.phoneNumber, builder.gender);
        this.paymentInfo=builder.paymentInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj) {return true;}
        if(obj==null || getClass() != obj.getClass()){return false;}
        Passenger other= (Passenger) obj;
        return this.eMail.equals(other.eMail);
    }
    
    @Override
    public int hashCode(){
        return eMail.hashCode();
    }
    
    //---------------------Builder Class---------------
    
    public static class PassengerBuilder extends Builder{
        private String paymentInfo;
        
         
        public PassengerBuilder setPaymentInfo(String paymentInfo){
            this.paymentInfo=paymentInfo;
            return this;
        }
        @Override
         public Passenger build(){
             return new Passenger(this);
         }        
    }
}
