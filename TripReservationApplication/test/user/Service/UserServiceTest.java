package user.Service;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;

public class UserServiceTest {

     @Test
    public void testCreateUserWithNull() {
        UserService userService = new UserService();
        UserDTO userDTO = null;
        boolean result = userService.createUser(userDTO);
        assertFalse(result);
    }

    @Test
    public void testCreateValidPassenger() {
        UserService userService = new UserService();
        
        UserDTO passengerDTO = new UserDTO();
        passengerDTO.setName("İrem");
        passengerDTO.setSurname("Kurtulmaz");
        passengerDTO.setEmail("irem@gmail.com");
        passengerDTO.setPassword("irem123");
        passengerDTO.setPhoneNumber("05554443322");
        passengerDTO.setGender('F');
        passengerDTO.setUserType(Passenger.class);
        
        boolean result = userService.createUser(passengerDTO); 
        assertTrue(result);
    }

    @Test
    public void testCreateValidAdmin() {
        UserService userService = new UserService();
        
        UserDTO adminDTO = new UserDTO();
        adminDTO.setName("Erdem");
        adminDTO.setSurname("Kaya");
        adminDTO.setEmail("erdem@gmail.com");
        adminDTO.setPassword("erdem123");
        adminDTO.setPhoneNumber("05501231201");
        adminDTO.setGender('M');
        adminDTO.setUserType(Admin.class);

        boolean result = userService.createUser(adminDTO);
        assertTrue(result);
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        UserService userService = new UserService();
        
        UserDTO passengerDTO = new UserDTO();
        passengerDTO.setName("İrem");
        passengerDTO.setSurname("Kurtulmaz");
        passengerDTO.setEmail("irem@gmail.com");
        passengerDTO.setPassword("irem123");
        passengerDTO.setPhoneNumber("05554443322");
        passengerDTO.setGender('F');
        passengerDTO.setUserType(Passenger.class);
        userService.createUser(passengerDTO);

        User result = userService.login("irem@gmail.com", "irem123");
        assertNotNull(result);
        assertEquals("İrem", result.getName());
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        UserService userService = new UserService();
        
        UserDTO adminDTO = new UserDTO();
        adminDTO.setName("Erdem");
        adminDTO.setSurname("Kaya");
        adminDTO.setEmail("erdem@gmail.com");
        adminDTO.setPassword("erdem123");
        adminDTO.setPhoneNumber("05501231201");
        adminDTO.setGender('M');
        adminDTO.setUserType(Admin.class);

        userService.createUser(adminDTO);

        User result = userService.login("erdem@gmail.com", "wrongpass");
        assertNull(result);
    }

    @Test
    public void testDeleteExistingUser() {
        UserService userService = new UserService();
        
        UserDTO passengerDTO = new UserDTO();
        passengerDTO.setName("İrem");
        passengerDTO.setSurname("Kurtulmaz");
        passengerDTO.setEmail("irem@gmail.com");
        passengerDTO.setPassword("irem123");
        passengerDTO.setPhoneNumber("05554443322");
        passengerDTO.setGender('F');
        passengerDTO.setUserType(Passenger.class);
        userService.createUser(passengerDTO);

        User passenger = userService.getUserByEmail(passengerDTO.getEmail());
        
        boolean result = userService.deleteUser(passenger.getEmail());
        assertTrue(result);
    }

    @Test
    public void testUpdateUserSuccessfully() {
        UserService userService = new UserService();
        
        UserDTO passengerDTO = new UserDTO();
        passengerDTO.setName("İrem");
        passengerDTO.setSurname("Kurtulmaz");
        passengerDTO.setEmail("irem@gmail.com");
        passengerDTO.setPassword("irem123");
        passengerDTO.setPhoneNumber("05554443322");
        passengerDTO.setGender('F');
        passengerDTO.setUserType(Passenger.class);
        userService.createUser(passengerDTO);

        passengerDTO.setPhoneNumber("05554442200"); // Bilgiyi değiştir
        boolean result = userService.updateUser(passengerDTO);
        assertTrue(result);
    }

    @Test
    public void testUpdateUserWithNull() {
        UserService userService = new UserService();
        boolean result = userService.updateUser(null);
        assertFalse(result);
    }
}