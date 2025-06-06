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
            
            if(!user.geteMail().endsWith("@gmail.com")){
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
            if(updateUser== null || updateUser.geteMail()== null){
                throw new IllegalArgumentException("Updated user or email cannot be null.");
            }
             
            User existingUser =userRepository.getUserByEmail(updateUser.geteMail());
            if(existingUser == null){
                System.out.println("User with the specified email does not exist.");
                return false;
            }
            
            if(updateUser.getName() != null){
                if(!updateUser.getName().chars().allMatch(Character::isLetter)){ //Kullanıcı adı sadece harflerden oluşmuyorsa--> true
                    throw new IllegalArgumentException("Name can only contain alphabetical character.");
                }
                existingUser.setName(updateUser.getName());
            }
            if(updateUser.getSurname() != null){
                if(!updateUser.getSurname().chars().allMatch(Character::isLetter)){
                    throw new IllegalArgumentException("Surname can only contain alphabetical character.");
                }
                existingUser.setSurname(updateUser.getSurname());
            }
            if(updateUser.getPassword() != null){
                existingUser.setPassword(updateUser.getPassword());
            }
            if(updateUser.getPhoneNumber() != null){
                if(!updateUser.getPhoneNumber().chars().allMatch(Character::isDigit)
                        || updateUser.getPhoneNumber().length() !=11){
                    throw new IllegalArgumentException("Invalid phone number.");
                }
                existingUser.setPhoneNumber(updateUser.getPhoneNumber());
            }
            return true;
        }
}
