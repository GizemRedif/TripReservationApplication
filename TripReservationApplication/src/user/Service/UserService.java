package user.Service;

import dto.UserDTO;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;
import user.repository.UserRepository;

// Service class that handles user operations.
public class UserService {
    
        private final UserRepository userRepository = UserRepository.getInstance(); // Singleton repository object used to store and manage users.
        
        public User getUserByEmail(String email){ // Returns the user based on the given email address. 
            return userRepository.getUserByEmail(email);
        }
        
        // Creates a new user based on the UserDTO and adds it to the repository.
        public boolean createUser(UserDTO userDTO){
            
            if(userDTO == null){return false;} // If the UserDTO is null, no user is created.
            Class<? extends User> userType = userDTO.getUserType(); // Determines the user type (Admin or Passenger).
            checkUserInfo(userDTO); // Validates the user information.
            User user;
            
            // Creates a user of type Admin.
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
            // Creates a user of type Passenger.
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
            
            userRepository.addUser(user); // Adds the created user to the repository.
            return true;
        }
        
        public boolean deleteUser(User user){ // Deletes the specified user from the repository.
            return userRepository.deleteUser(user);
        }
        
        // Attempts to log in with the given email and password.
        public User login(String eMail, String password){
            User user= userRepository.getUserByEmail(eMail); // Finds the user by email address.
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
        
        // Updates the user information
        public boolean updateUser(UserDTO newUser){
//            if(newUser== null || newUser.getEmail()== null){
//                throw new IllegalArgumentException("Updated user or email cannot be null.");
//            }
             
            User existingUser =userRepository.getUserByEmail(newUser.getEmail()); // Finds the existing user by email address
            
            checkUserInfo(newUser); // Validates the new user information.
            
            // Updates existing user fields if the corresponding values are not null.
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
        
        // Helper method that checks the validity of user information.
        private void checkUserInfo(UserDTO userDTO){
            if (userDTO.getName() != null && !userDTO.getName().chars().allMatch(ch -> Character.isLetter(ch) || ch == ' ')) {
                throw new IllegalArgumentException("Name can only contain alphabetical characters.");
            }

            if (userDTO.getSurname() != null && !userDTO.getSurname().chars().allMatch(ch -> Character.isLetter(ch) || ch == ' ')) {
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
}
