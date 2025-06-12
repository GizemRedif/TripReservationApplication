package user.repository;

import user.model.User;

import java.util.Map;
import java.util.HashMap;

// A singleton repository class that stores and manages users by their email addresses.
public class UserRepository {
    private static UserRepository instance; // Singleton instance.
    private final Map<String, User> usersWithEmail; // User database that maps each user to their email address.

    private UserRepository() { // Private constructor that ensures the repository is created only once.
        this.usersWithEmail = new HashMap<>();
    }

    // Returns the singleton instance, creates a new one if it doesn't exist yet.
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    
    // Returns the user associated with the given email address.
    public User getUserByEmail(String email){
        return usersWithEmail.get(email);
    }
    
    // Adds a new user.
    public boolean addUser(User user){
        if(usersWithEmail.containsKey(user.getEmail())){
            return false; // If the user is already registered, no addition is performed.
        }
        
        usersWithEmail.put(user.getEmail(), user);  // The new user is added.
        
        return true;
    }
    
    // Removes the specified user from the system by their email address.
    public boolean deleteUser(User user){
        String userMail = user.getEmail();
        if(userMail == null){
            return false; // If the email is null, the deletion fails.
        }
        usersWithEmail.remove(user.getEmail());
        
        return true; // The user is removed.
    }
    
}
