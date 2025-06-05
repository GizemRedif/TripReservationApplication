package user.model;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    
}
