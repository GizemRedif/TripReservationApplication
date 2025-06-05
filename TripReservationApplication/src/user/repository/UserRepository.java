package user.repository;

import user.model.User;

import java.util.Map;
import java.util.HashMap;

public class UserRepository {
    private static UserRepository instance;
    private Map<String, User> usersWithEmail;

    private UserRepository() {
        this.usersWithEmail = new HashMap<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    
    public User getUserByEmail(String email){
        return usersWithEmail.get(email);
    }
    
    /*add a new user*/
    public boolean addUser(User user){
        if(usersWithEmail.containsKey(user.geteMail())){
            return false;
        }
        
        usersWithEmail.put(user.geteMail(), user); 
        
        return true;
    }
    
    public boolean deleteUser(User user){
        String userMail = user.geteMail();
        if(userMail == null){
            return false;
        }
        usersWithEmail.remove(user.geteMail());
        
        return true;
    }
    
}
