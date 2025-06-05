package user.Service;

import user.model.User;
import user.repository.UserRepository;

public class UserService {
    
        private final UserRepository userRepository = UserRepository.getInstance();
        
        public boolean createUser(User user){
            
            if(user == null){return false;}
            
            if(!user.getName().chars().allMatch(Character::isLetter)){
                throw new IllegalArgumentException("name can only contain alphabetical character");
            }
            
            if(!user.getSurname().chars().allMatch(Character::isLetter)){
                throw new IllegalArgumentException("surname can only contain alphabetical character");
            }
            
            if(!user.getEmail().endsWith("@gmail.com")){
                throw new IllegalArgumentException("email must end with @gmail.com");
            }
            
            if(user.getPhoneNumber().length() != 11 || user.getPhoneNumber().chars().allMatch(Character::isDigit)){
                throw new IllegalArgumentException("invalid phone number");
            }
            
            userRepository.addUser(user);
            return true;
        }
        
        public boolean DeletUser(User user){
            return userRepository.deleteUser(user);
        }
}
