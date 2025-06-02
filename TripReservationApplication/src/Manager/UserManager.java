package Manager;

import Domain.User;
import java.util.Map;
import java.util.HashMap;

public class UserManager {
    private static Map<String, User> usersWithEmail;
    
    public UserManager(){}
    
    public void addUser(User user){
        usersWithEmail.put(user.getEmail(), user);        
    }
    
    public void deleteUser(User user){
        usersWithEmail.remove(user.getEmail());
    }
    
    public User logIn(String email, String password){
        User user = usersWithEmail.get(email);
        if(user != null && user.getPassword.equals(password)){
            return user;
        }
        
        else{
            System.out.println("incorrect email or password");
            return null;
        }
    }
}
