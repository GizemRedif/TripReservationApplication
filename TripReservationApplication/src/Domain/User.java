package Domain;

public abstract class User {
    String name;
    String surname;
    String eMail;
    String password;
    String phoneNumber;
    char gender;
    
    public User(String name, String surname, String eMail, String password, String phoneNumber, char gender){
    this.name = name;
    this.surname = surname;
    this.eMail = eMail;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
    }
}
