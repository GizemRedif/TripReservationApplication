
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
        busTrip1.setDepartureStation("Adana");
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
        
        TripDTO busTrip5 = new TripDTO();
        busTrip5.setDepartureStation("Adana");
        busTrip5.setArrivalStation("Ankara");
        busTrip5.setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 30));
        busTrip5.setTripTime(LocalTime.of(2, 30));
        busTrip5.setFare(200);
        busTrip5.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTrip5.setTripType(BusTrip.class);

        TripDTO busTrip6 = new TripDTO();
        busTrip6.setDepartureStation("Adana");
        busTrip6.setArrivalStation("Ankara");
        busTrip6.setDepartureDate(LocalDateTime.of(2025, 6, 15, 12, 00));
        busTrip6.setTripTime(LocalTime.of(2, 45));
        busTrip6.setFare(220);
        busTrip6.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTrip6.setTripType(BusTrip.class);
        
        
        
        TripDTO busTrip7 = new TripDTO();
        busTrip7.setDepartureStation("Adana");
        busTrip7.setArrivalStation("Ankara");
        busTrip7.setDepartureDate(LocalDateTime.of(2025, 6, 15, 13, 30));
        busTrip7.setTripTime(LocalTime.of(3, 30));
        busTrip7.setFare(240);
        busTrip7.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTrip7.setTripType(BusTrip.class);

        TripDTO busTrip8 = new TripDTO();
        busTrip8.setDepartureStation("Adana");
        busTrip8.setArrivalStation("Ankara");
        busTrip8.setDepartureDate(LocalDateTime.of(2025, 6, 15, 15, 00));
        busTrip8.setTripTime(LocalTime.of(2, 50));
        busTrip8.setFare(260);
        busTrip8.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTrip8.setTripType(BusTrip.class);

        TripDTO busTrip9 = new TripDTO();
        busTrip9.setDepartureStation("Adana");
        busTrip9.setArrivalStation("Ankara");
        busTrip9.setDepartureDate(LocalDateTime.of(2025, 6, 15, 17, 00));
        busTrip9.setTripTime(LocalTime.of(2, 40));
        busTrip9.setFare(280);
        busTrip9.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTrip9.setTripType(BusTrip.class);
        
        
        TripDTO busTrip10 = new TripDTO();
        busTrip10.setDepartureStation("Adana");
        busTrip10.setArrivalStation("Ankara");
        busTrip10.setDepartureDate(LocalDateTime.of(2025, 6, 15, 18, 30));
        busTrip10.setTripTime(LocalTime.of(3, 10));
        busTrip10.setFare(300);
        busTrip10.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTrip10.setTripType(BusTrip.class);

        
        
        
        //FlightTrips
        
        TripDTO flightTrip1 = new TripDTO();
        flightTrip1.setDepartureStation("İstanbul");
        flightTrip1.setArrivalStation("İzmir");
        flightTrip1.setDepartureDate(LocalDateTime.of(2025, 6, 15, 9, 0));
        flightTrip1.setTripTime(LocalTime.of(1, 0));
        flightTrip1.setFare(950);
        flightTrip1.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip1.setTripType(FlightTrip.class);

        TripDTO flightTrip2 = new TripDTO();
        flightTrip2.setDepartureStation("İstanbul");
        flightTrip2.setArrivalStation("İzmir");
        flightTrip2.setDepartureDate(LocalDateTime.of(2025, 6, 15, 11, 0));
        flightTrip2.setTripTime(LocalTime.of(1, 5));
        flightTrip2.setFare(970);
        flightTrip2.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTrip2.setTripType(FlightTrip.class);

        TripDTO flightTrip3 = new TripDTO();
        flightTrip3.setDepartureStation("İstanbul");
        flightTrip3.setArrivalStation("İzmir");
        flightTrip3.setDepartureDate(LocalDateTime.of(2025, 6, 15, 13, 0));
        flightTrip3.setTripTime(LocalTime.of(1, 10));
        flightTrip3.setFare(990);
        flightTrip3.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTrip3.setTripType(FlightTrip.class);

        TripDTO flightTrip4 = new TripDTO();
        flightTrip4.setDepartureStation("Istanbul");
        flightTrip4.setArrivalStation("Izmir");
        flightTrip4.setDepartureDate(LocalDateTime.of(2025, 6, 15, 15, 0));
        flightTrip4.setTripTime(LocalTime.of(1, 0));
        flightTrip4.setFare(1010);
        flightTrip4.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip4.setTripType(FlightTrip.class);

        TripDTO flightTrip5 = new TripDTO();
        flightTrip5.setDepartureStation("İstanbul");
        flightTrip5.setArrivalStation("İzmir");
        flightTrip5.setDepartureDate(LocalDateTime.of(2025, 6, 15, 17, 0));
        flightTrip5.setTripTime(LocalTime.of(1, 15));
        flightTrip5.setFare(1030);
        flightTrip5.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTrip5.setTripType(FlightTrip.class);

        
        TripDTO flightTrip6 = new TripDTO();
        flightTrip6.setDepartureStation("İstanbul");
        flightTrip6.setArrivalStation("İzmir");
        flightTrip6.setDepartureDate(LocalDateTime.of(2025, 6, 15, 19, 0));
        flightTrip6.setTripTime(LocalTime.of(1, 10));
        flightTrip6.setFare(1050);
        flightTrip6.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTrip6.setTripType(FlightTrip.class);
        
        TripDTO flightTrip7 = new TripDTO();
        flightTrip7.setDepartureStation("İstanbul");
        flightTrip7.setArrivalStation("İzmir");
        flightTrip7.setDepartureDate(LocalDateTime.of(2025, 6, 15, 9, 0));
        flightTrip7.setTripTime(LocalTime.of(1, 0));
        flightTrip7.setFare(950);
        flightTrip7.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip7.setTripType(FlightTrip.class);

        TripDTO flightTrip8 = new TripDTO();
        flightTrip8.setDepartureStation("Ankara");
        flightTrip8.setArrivalStation("Van");
        flightTrip8.setDepartureDate(LocalDateTime.of(2023, 6, 16, 16, 30));
        flightTrip8.setTripTime(LocalTime.of(1, 45));
        flightTrip8.setFare(1200);
        flightTrip8.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTrip8.setTripType(FlightTrip.class);

        TripDTO flightTrip9 = new TripDTO();
        flightTrip9.setDepartureStation("Antalya");
        flightTrip9.setArrivalStation("İstanbul");
        flightTrip9.setDepartureDate(LocalDateTime.of(2025, 6, 17, 11, 15));
        flightTrip9.setTripTime(LocalTime.of(1, 15));
        flightTrip9.setFare(1000);
        flightTrip9.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTrip9.setTripType(FlightTrip.class);

        TripDTO flightTrip10 = new TripDTO();
        flightTrip10.setDepartureStation("Diyarbakır");
        flightTrip10.setArrivalStation("İzmir");
        flightTrip10.setDepartureDate(LocalDateTime.of(2022, 6, 18, 15, 0));
        flightTrip10.setTripTime(LocalTime.of(2, 0));
        flightTrip10.setFare(1300);
        flightTrip10.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTrip10.setTripType(FlightTrip.class);

        tripService.createTrip(busTrip1);
        tripService.createTrip(busTrip2);
        tripService.createTrip(busTrip3);
        tripService.createTrip(busTrip4);
        tripService.createTrip(busTrip5);
        tripService.createTrip(busTrip6);
        tripService.createTrip(busTrip7);
        tripService.createTrip(busTrip8);
        tripService.createTrip(busTrip9);
        tripService.createTrip(busTrip10);
        tripService.createTrip(flightTrip1);
        tripService.createTrip(flightTrip2);
        tripService.createTrip(flightTrip3);
        tripService.createTrip(flightTrip4);
        tripService.createTrip(flightTrip5);
        tripService.createTrip(flightTrip6);
        tripService.createTrip(flightTrip7);
        tripService.createTrip(flightTrip8);
        tripService.createTrip(flightTrip9);
        tripService.createTrip(flightTrip10);
        
        
        



        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
