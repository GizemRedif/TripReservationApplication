package user.repository;

import user.model.User;
import java.util.List;
import java.util.ArrayList;

public class UserRepository {
    private static UserRepository instance;
    private List<User> userList;

    private UserRepository() {
        this.userList = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    
    /*add a new user*/
    public void addTrip(User user){
        userList.add(user);
    }
    
    public boolean cancelTrip(User user){
        return userList.remove(user);
    }
    
}
