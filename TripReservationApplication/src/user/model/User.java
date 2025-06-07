package user.model;

public abstract class User {
    private String name;
    private String surname;
    private String eMail;
    private String password;
    private String phoneNumber;
    private char gender;
    
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

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
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
    
    //----------------Builder Class--------------
    
    public static abstract class Builder{
        protected String name;
        protected String surname;
        protected String eMail;
        protected String password;
        protected String phoneNumber;
        protected char gender;
        
        public Builder setName(String name){
            this.name=name;
            return this;
        }
        public Builder setSurname(String surname){
            this.surname=surname;
            return this;
        }
        public Builder seteMail(String eMail){
            this.eMail=eMail;
            return this;
        }
        public Builder setPassword (String password){
            this.password= password;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
            return this;
        }
        public Builder setGender(char gender){
            this.gender=gender;
            return this;
        }
        public abstract User build();
    }
}
