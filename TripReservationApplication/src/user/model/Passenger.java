package user.model;

// A user class that contains passenger-specific information
public class Passenger extends User {
    private String paymentInfo; // Stores the passenger's payment information.  /*tipi için şimdilik string yazdım*/

    public Passenger(String name, String surname, String eMail, String password, String phoneNumber, char gender, String paymentInfo) {
        super(name, surname, eMail, password, phoneNumber, gender);
        this.paymentInfo=paymentInfo;
    }
    
    public Passenger(Passenger other){
        super(other.getName(), other.getSurname(), other.getEmail(), other.getPassword(), other.getPhoneNumber(), other.getGender());
        this.paymentInfo=other.paymentInfo;
    }
    
    // Constructor method that creates a Passenger object using the PassengerBuilder.
    public Passenger(PassengerBuilder builder){
        super(builder.name, builder.surname, builder.eMail, builder.password, builder.phoneNumber, builder.gender);
        this.paymentInfo=builder.paymentInfo;
    }
    
    @Override
    public boolean equals(Object obj){ // Checks whether two Passenger objects are equal based on their email address.
        if(this==obj) {return true;}
        if(obj==null || getClass() != obj.getClass()){return false;}
        Passenger other= (Passenger) obj;
        return this.getEmail().equals(other.getEmail());
    }
    
    @Override
    public int hashCode(){
        return getEmail().hashCode();
    }
    
    //---------------------Builder Class---------------
    
    // An inner Builder class used to construct a Passenger object step by step.
    public static class PassengerBuilder extends Builder{
        private String paymentInfo;
        
         
        public PassengerBuilder setPaymentInfo(String paymentInfo){
            this.paymentInfo=paymentInfo;
            return this;
        }
        @Override
         public Passenger build(){ // Creates the Passenger object.
             return new Passenger(this);
         }        
    }
}
