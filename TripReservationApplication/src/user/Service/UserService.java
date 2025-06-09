package user.Service;

import user.model.User;
import user.repository.UserRepository;

public class UserService {
    
        private final UserRepository userRepository = UserRepository.getInstance();
        
        public boolean createUser(User user){
            
            if(user == null){return false;}
            
            checkUserInfo(user);
            userRepository.addUser(user);
            return true;
        }
        
        public boolean deleteUser(User user){
            return userRepository.deleteUser(user);
        }
        
        
        public User login(String eMail, String password){
            User user= userRepository.getUserByEmail(eMail);
            if(user == null){
                System.out.println("Email not found.");
                return null;
            }
            
            if(user.getPassword().equals(password)){
                System.out.println("Login succesful for: " + eMail);
                return user;
            }
            else{
                System.out.println("Incorrect password.");
                return null;
            }
        }
        
        public boolean updateUser(User newUser){
//            if(newUser== null || newUser.getEmail()== null){
//                throw new IllegalArgumentException("Updated user or email cannot be null.");
//            }
             
            User existingUser =userRepository.getUserByEmail(newUser.getEmail());
            
            checkUserInfo(newUser);
            
            if(newUser.getName()!= null){
                existingUser.setName(newUser.getName());
            }
            if(newUser.getSurname()!=null){
                existingUser.setSurname(newUser.getSurname());
            }
            if(newUser.getPassword()!=null){
                existingUser.setPassword(newUser.getPassword());
            }
            if(newUser.getPhoneNumber()!=null){
                existingUser.setPhoneNumber(newUser.getPhoneNumber());
            }
            
            return true;
        }
        
        private void checkUserInfo(User user){
            if (user.getName() != null && !user.getName().chars().allMatch(Character::isLetter)) {
                throw new IllegalArgumentException("Name can only contain alphabetical characters.");
            }

            if (user.getSurname() != null && !user.getSurname().chars().allMatch(Character::isLetter)) {
                throw new IllegalArgumentException("Surname can only contain alphabetical characters.");
            }

            if (user.getEmail() != null && !user.getEmail().endsWith("@gmail.com")) {
                throw new IllegalArgumentException("Email must end with @gmail.com");
            }
            
            if (user.getPhoneNumber() != null) {
                if (user.getPhoneNumber().length() != 11 || 
                    !user.getPhoneNumber().chars().allMatch(Character::isDigit)) {
                    throw new IllegalArgumentException("Invalid phone number.");
                }
            }

        }
}
