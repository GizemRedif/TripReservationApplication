package user;

import user.model.User;
import java.util.Map;
import java.util.HashMap;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> usersWithEmail;
    
    private UserManager() {}
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    
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
