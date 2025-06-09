
package tripreservationapplication;

import dto.UserDTO;
import javax.swing.SwingUtilities;
import reservation.service.ReservationService;
import trip.service.TripService;
import user.Service.UserService;
import user.model.*;


public class TripReservationApplication {

   
    public static void main(String[] args) {
        UserService userService = new UserService();
        TripService tripService = new TripService();
        ReservationService reservationService = new ReservationService();
        
        UserDTO mert = new UserDTO();
        mert.setName("mert");
        mert.setSurname("berrak");
        mert.setEmail("mertaliberrak@gmail.com");
        mert.setPhoneNumber("05535240469");
        mert.setPassword("mert123");
        mert.setGender('M');
        mert.setUserType(Admin.class);

        UserDTO efe = new UserDTO();
        efe.setName("efe");
        efe.setSurname("cicekdagi");
        efe.setEmail("efe@gmail.com");
        efe.setPhoneNumber("05001112233");
        efe.setPassword("efe123");
        efe.setGender('M');
        efe.setUserType(Admin.class);

        UserDTO gizem = new UserDTO();
        gizem.setName("gizem");
        gizem.setSurname("redif");
        gizem.setEmail("gizem@gmail.com");
        gizem.setPhoneNumber("05002223344");
        gizem.setPassword("gizem123");
        gizem.setGender('F');
        gizem.setUserType(Admin.class);

        UserDTO irem = new UserDTO();
        irem.setName("irem");
        irem.setSurname("kurtulmaz");
        irem.setEmail("irem@gmail.com");
        irem.setPhoneNumber("05003334455");
        irem.setPassword("irem123");
        irem.setGender('F');
        irem.setUserType(Admin.class);

        UserDTO mustafa = new UserDTO();
        mustafa.setName("mustafa");
        mustafa.setSurname("sengül");
        mustafa.setEmail("mustafa@gmail.com");
        mustafa.setPhoneNumber("05004445566");
        mustafa.setPassword("mustafa123");
        mustafa.setGender('M');
        mustafa.setUserType(Passenger.class);

        UserDTO fatih = new UserDTO();
        fatih.setName("fatih");
        fatih.setSurname("ileri");
        fatih.setEmail("fatih@gmail.com");
        fatih.setPhoneNumber("05005556677");
        fatih.setPassword("fatih123");
        fatih.setGender('M');
        fatih.setUserType(Passenger.class);

        UserDTO dikdas = new UserDTO();
        dikdas.setName("dikdaş");
        dikdas.setSurname("mert");
        dikdas.setEmail("dikdas@gmail.com");
        dikdas.setPhoneNumber("05006667788");
        dikdas.setPassword("dikdas123");
        dikdas.setGender('M');
        dikdas.setUserType(Passenger.class);

        UserDTO onur = new UserDTO();
        onur.setName("onur");
        onur.setSurname("yavuz");
        onur.setEmail("onur@gmail.com");
        onur.setPhoneNumber("05007778899");
        onur.setPassword("onur123");
        onur.setGender('M');
        onur.setUserType(Passenger.class);

        userService.createUser(mert);
        userService.createUser(irem);        
        userService.createUser(gizem);
        userService.createUser(efe);
        userService.createUser(dikdas);
        userService.createUser(onur);
        userService.createUser(fatih);
        userService.createUser(mustafa);
        
        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
