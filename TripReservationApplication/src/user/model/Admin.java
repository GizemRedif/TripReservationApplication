package user.model;

public class Admin extends User {
    
    public Admin(String name, String surname, String eMail, String password, String phoneNumber, char gender) {
        super(name, surname, eMail, password, phoneNumber, gender);
    }
    
    public Admin(AdminBuilder builder){
        super(builder.name, builder.surname, builder.eMail, builder.password, builder.phoneNumber, builder.gender);
    }
    //------------------Builder Class----------------
    
    public static class AdminBuilder extends Builder{
        
        @Override
        public Admin build(){
            return new Admin(this);
        }
    }
}
