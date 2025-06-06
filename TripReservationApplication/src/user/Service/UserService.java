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
        
        public boolean updateUser(User updateUser){
            if(updateUser== null || updateUser.getEmail()== null){
                throw new IllegalArgumentException("Updated user or email cannot be null.");
            }
             
            User existingUser =userRepository.getUserByEmail(updateUser.getEmail());
            if(existingUser == null){
                System.out.println("User with the specified email does not exist.");
                return false;
            }
            
            if(updateUser.getName()!=null || updateUser.getSurname()!=null ||
                    updateUser.getPhoneNumber()!=null || updateUser.getEmail()!=null){
                checkUserInfo(updateUser);
            }
            
            if(updateUser.getName()!= null){
                existingUser.setName(updateUser.getName());
            }
            if(updateUser.getSurname()!=null){
                existingUser.setSurname(updateUser.getSurname());
            }
            if(updateUser.getPassword()!=null){
                existingUser.setPassword(updateUser.getPassword());
            }
            if(updateUser.getPhoneNumber()!=null){
                existingUser.setPhoneNumber(updateUser.getPhoneNumber());
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
