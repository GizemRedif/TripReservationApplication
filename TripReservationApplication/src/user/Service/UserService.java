package user.Service;

import dto.UserDTO;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;
import user.repository.UserRepository;

public class UserService {
    
        private final UserRepository userRepository = UserRepository.getInstance();
        
        public boolean createUser(UserDTO userDTO){
            
            if(userDTO == null){return false;}
            Class<? extends User> userType = userDTO.getUserType();
            checkUserInfo(userDTO);
            User user;
            if (userType == Admin.class){
                user = new Admin.AdminBuilder()
                        .setName(userDTO.getName())
                        .setSurname(userDTO.getSurname())
                        .seteMail(userDTO.getEmail())
                        .setPassword(userDTO.getPassword())
                        .setPhoneNumber(userDTO.getPhoneNumber())
                        .setGender(userDTO.getGender())
                        .build();
                        
            }
            else{
                user = new Passenger.PassengerBuilder()
                    .setName(userDTO.getName())
                    .setSurname(userDTO.getSurname())
                    .seteMail(userDTO.getEmail())
                    .setPassword(userDTO.getPassword())
                    .setPhoneNumber(userDTO.getPhoneNumber())
                    .setGender(userDTO.getGender())
                    .build();
            }
            
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
            if(newUser== null || newUser.getEmail()== null){
                throw new IllegalArgumentException("Updated user or email cannot be null.");
            }
             
            User existingUser =userRepository.getUserByEmail(newUser.getEmail());
            if(existingUser == null){
                System.out.println("User with the specified email does not exist.");
                return false;
            }
            
            checkUserInfo(toUserDTO(newUser));
            
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
        
        private void checkUserInfo(UserDTO userDTO){
            if (userDTO.getName() != null && !userDTO.getName().chars().allMatch(Character::isLetter)) {
                throw new IllegalArgumentException("Name can only contain alphabetical characters.");
            }

            if (userDTO.getSurname() != null && !userDTO.getSurname().chars().allMatch(Character::isLetter)) {
                throw new IllegalArgumentException("Surname can only contain alphabetical characters.");
            }

            if (userDTO.getEmail() != null && !userDTO.getEmail().endsWith("@gmail.com")) {
                throw new IllegalArgumentException("Email must end with @gmail.com");
            }
            
            if (userDTO.getPhoneNumber() != null) {
                if (userDTO.getPhoneNumber().length() != 11 || 
                    !userDTO.getPhoneNumber().chars().allMatch(Character::isDigit)) {
                    throw new IllegalArgumentException("Invalid phone number.");
                }
            }
        }
        public static UserDTO toUserDTO(User user) {
           if (user == null) return null;

           UserDTO dto = new UserDTO();
           dto.setName(user.getName());
           dto.setSurname(user.getSurname());
           dto.setEmail(user.getEmail());
           dto.setPassword(user.getPassword());
           dto.setPhoneNumber(user.getPhoneNumber());
           dto.setGender(user.getGender());
           dto.setUserType(user.getClass()); // burası önemli!
           return dto;
       }
}
