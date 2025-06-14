package dto;

import user.model.User;

// Data Transfer Object for User related data.
public class UserDTO {
    private String name;
    private String surname;
    private String eMail;
    private String password;
    private String phoneNumber;
    private char gender;
    private Class<? extends User> userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    
    public Class<? extends User> getUserType(){
        return userType;
    }
    
    public void setUserType(Class<? extends User> userType){
        this.userType = userType;
    }
}
