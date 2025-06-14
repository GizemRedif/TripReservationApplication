package user.model;

// A class that contains information specific to administrators.
public class Admin extends User {
    
    public Admin(String name, String surname, String eMail, String password, String phoneNumber, char gender) {
        super(name, surname, eMail, password, phoneNumber, gender);
    }
    
    // Constructor method that creates an Admin object using the AdminBuilder.
    public Admin(AdminBuilder builder){
        super(builder.name, builder.surname, builder.eMail, builder.password, builder.phoneNumber, builder.gender);
    }
    
    //------------------Builder Class----------------
    
    // An inner Builder class used to construct an Admin object step by step.
    public static class AdminBuilder extends Builder{
        
        @Override
        public Admin build(){ //Creates the Admin object.
            return new Admin(this);
        }
    }
}
