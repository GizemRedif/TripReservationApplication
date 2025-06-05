package user.model;
import reservation.model.Reservation;
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
    
    public Passenger(Passenger other){
        super(other.getName(), other.getSurname(), other.geteMail(), other.getPassword(), other.getPhoneNumber(), other.getGender());
        this.reservations= new ArrayList<>(other.reservations);
        this.paymentInfo=other.paymentInfo;
    }
    
    public Passenger(PassengerBuilder builder){
        super(builder.name, builder.surname, builder.eMail, builder.password, builder.phoneNumber, builder.gender);
        this.reservations= new ArrayList<>(builder.reservations);
        this.paymentInfo=builder.paymentInfo;
    }
    
    public List<Reservation> getReservations(){
        if(reservations == null){return null;}
        List<Reservation> copy = new ArrayList<>();
        for( Reservation item : reservations){
            copy.add( item.copy());
        }
        return copy;
    }
    
    public void setReservation(List<Reservation> reservations){
        if(reservations == null){
            this.reservations= null;
            return;
        }
        this.reservations=new ArrayList<>();
        for( Reservation item : reservations){
            this.reservations.add(item.copy());
        }
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
    
    //---------------------Builder Class---------------
    
    public static class PassengerBuilder extends Builder{
        private List<Reservation> reservations = new ArrayList<>();
        private String paymentInfo;
        
         public PassengerBuilder setReservation(List<Reservation> reservations){
             if(reservations !=null){
                 this.reservations= new ArrayList<>(reservations);
             }
             else{
                 this.reservations= new ArrayList<>();
             }
             return this;
         }         
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
