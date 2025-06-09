
package tripreservationapplication;

import dto.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.SwingUtilities;
import reservation.service.ReservationService;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.service.TripService;
import user.Service.UserService;
import user.model.*;
import vehicle.model.Bus;
import vehicle.model.Plane;
import vehicle.service.VehicleService;


public class TripReservationApplication {

   
    public static void main(String[] args) {
        UserService userService = new UserService();
        TripService tripService = new TripService();
        ReservationService reservationService = new ReservationService();
        VehicleService vehicleService = new VehicleService();
        
        //Users
        UserDTO mert = new UserDTO();
        mert.setName("mert ali");
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
        
        
        
        
        
        
        
        // 🚌 Otobüsler
        VehicleDTO bus1 = new VehicleDTO();
        bus1.setCapacity(45);
        bus1.setVehicleIdentifier("34 AA 1313");
        bus1.setVehicleType(Bus.class);

        VehicleDTO bus2 = new VehicleDTO();
        bus2.setCapacity(45);
        bus2.setVehicleIdentifier("35 BB 2525");
        bus2.setVehicleType(Bus.class);

        VehicleDTO bus3 = new VehicleDTO();
        bus3.setCapacity(45);
        bus3.setVehicleIdentifier("06 CC 3636");
        bus3.setVehicleType(Bus.class);
        
        
        

        // ✈️ Uçaklar
        VehicleDTO plane1 = new VehicleDTO();
        plane1.setCapacity(150);
        plane1.setVehicleIdentifier("TK101");
        plane1.setVehicleType(Plane.class);

        VehicleDTO plane2 = new VehicleDTO();
        plane2.setCapacity(150);
        plane2.setVehicleIdentifier("PC202");
        plane2.setVehicleType(Plane.class);

        VehicleDTO plane3 = new VehicleDTO();
        plane3.setCapacity(150);
        plane3.setVehicleIdentifier("THY303");
        plane3.setVehicleType(Plane.class);
        
        vehicleService.createVehicle(bus1);
        vehicleService.createVehicle(bus2);
        vehicleService.createVehicle(bus3);
        vehicleService.createVehicle(plane1);
        vehicleService.createVehicle(plane2);
        vehicleService.createVehicle(plane3);
        
        
        
        
        
        // BusTrips
        TripDTO busTrip1 = new TripDTO();
        busTrip1.setDepartureStation("Istanbul");
        busTrip1.setArrivalStation("Ankara");
        busTrip1.setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 30));
        busTrip1.setTripTime(LocalTime.of(12, 0));
        busTrip1.setFare(500);
        busTrip1.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTrip1.setTripType(BusTrip.class);

        TripDTO busTrip2 = new TripDTO();
        busTrip2.setDepartureStation("Izmir");
        busTrip2.setArrivalStation("Bursa");
        busTrip2.setDepartureDate(LocalDateTime.of(2023, 6, 16, 9, 0));
        busTrip2.setTripTime(LocalTime.of(4, 30));
        busTrip2.setFare(350);
        busTrip2.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTrip2.setTripType(BusTrip.class);

        TripDTO busTrip3 = new TripDTO();
        busTrip3.setDepartureStation("Antalya");
        busTrip3.setArrivalStation("Konya");
        busTrip3.setDepartureDate(LocalDateTime.of(2025, 6, 17, 8, 15));
        busTrip3.setTripTime(LocalTime.of(5, 0));
        busTrip3.setFare(280);
        busTrip3.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTrip3.setTripType(BusTrip.class);

        TripDTO busTrip4 = new TripDTO();
        busTrip4.setDepartureStation("Adana");
        busTrip4.setArrivalStation("Gaziantep");
        busTrip4.setDepartureDate(LocalDateTime.of(2021, 6, 18, 14, 45));
        busTrip4.setTripTime(LocalTime.of(3, 15));
        busTrip4.setFare(300);
        busTrip4.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTrip4.setTripType(BusTrip.class);

        
        
        
        //FlightTrips
        TripDTO flightTrip1 = new TripDTO();
        flightTrip1.setDepartureStation("Istanbul");
        flightTrip1.setArrivalStation("Izmir");
        flightTrip1.setDepartureDate(LocalDateTime.of(2025, 6, 15, 9, 0));
        flightTrip1.setTripTime(LocalTime.of(1, 0));
        flightTrip1.setFare(950);
        flightTrip1.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip1.setTripType(FlightTrip.class);

        TripDTO flightTrip2 = new TripDTO();
        flightTrip2.setDepartureStation("Ankara");
        flightTrip2.setArrivalStation("Van");
        flightTrip2.setDepartureDate(LocalDateTime.of(2023, 6, 16, 16, 30));
        flightTrip2.setTripTime(LocalTime.of(1, 45));
        flightTrip2.setFare(1200);
        flightTrip2.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTrip2.setTripType(FlightTrip.class);

        TripDTO flightTrip3 = new TripDTO();
        flightTrip3.setDepartureStation("Antalya");
        flightTrip3.setArrivalStation("Istanbul");
        flightTrip3.setDepartureDate(LocalDateTime.of(2025, 6, 17, 11, 15));
        flightTrip3.setTripTime(LocalTime.of(1, 15));
        flightTrip3.setFare(1000);
        flightTrip3.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTrip3.setTripType(FlightTrip.class);

        TripDTO flightTrip4 = new TripDTO();
        flightTrip4.setDepartureStation("Diyarbakır");
        flightTrip4.setArrivalStation("Izmir");
        flightTrip4.setDepartureDate(LocalDateTime.of(2022, 6, 18, 15, 0));
        flightTrip4.setTripTime(LocalTime.of(2, 0));
        flightTrip4.setFare(1300);
        flightTrip4.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip4.setTripType(FlightTrip.class);

        tripService.createTrip(busTrip1);
        tripService.createTrip(busTrip2);
        tripService.createTrip(busTrip3);
        tripService.createTrip(busTrip4);
        tripService.createTrip(flightTrip1);
        tripService.createTrip(flightTrip2);
        tripService.createTrip(flightTrip3);
        tripService.createTrip(flightTrip4);
        
        



        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
