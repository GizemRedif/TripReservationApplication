
package tripreservationapplication;

import dto.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.SwingUtilities;
import reservation.model.BusReservation;
import reservation.model.FlightReservation;
import reservation.service.ReservationService;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
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
        
        
        //SİİİİİİİLLLLLLLL
        UserDTO admin2 = new UserDTO();
        admin2.setName("Cemil");
        admin2.setSurname("Olabilir");
        admin2.setEmail("2@gmail.com");
        admin2.setPhoneNumber("05535240469");
        admin2.setPassword("2");
        admin2.setGender('M');
        admin2.setUserType(Admin.class);
        userService.createUser(admin2);
        
        UserDTO passenger11 = new UserDTO();
        passenger11.setName("Deniz");
        passenger11.setSurname("Celik");
        passenger11.setEmail("1@gmail.com");
        passenger11.setPhoneNumber("05004445566");
        passenger11.setPassword("1");
        passenger11.setGender('M');
        passenger11.setUserType(Passenger.class);
        userService.createUser(passenger11);

        
        
        
        //USERS---------------------------------------------------------------------------------------
        //Admin
        UserDTO admin = new UserDTO();
        admin.setName("Cemil");
        admin.setSurname("Olabilir");
        admin.setEmail("admin@gmail.com");
        admin.setPhoneNumber("05535240469");
        admin.setPassword("admin123");
        admin.setGender('M');
        admin.setUserType(Admin.class);
        
        userService.createUser(admin);


        //Passengers
        UserDTO passenger1 = new UserDTO();
        passenger1.setName("Deniz");
        passenger1.setSurname("Celik");
        passenger1.setEmail("deniz@gmail.com");
        passenger1.setPhoneNumber("05004445566");
        passenger1.setPassword("deniz123");
        passenger1.setGender('M');
        passenger1.setUserType(Passenger.class);

        UserDTO passenger2 = new UserDTO();
        passenger2.setName("Tuna");
        passenger2.setSurname("Salı");
        passenger2.setEmail("tuna@gmail.com");
        passenger2.setPhoneNumber("05005556677");
        passenger2.setPassword("tuna123");
        passenger2.setGender('M');
        passenger2.setUserType(Passenger.class);

        UserDTO passenger3 = new UserDTO();
        passenger3.setName("Cemre");
        passenger3.setSurname("Aydın");
        passenger3.setEmail("cemre@gmail.com");
        passenger3.setPhoneNumber("05006667788");
        passenger3.setPassword("cemre123");
        passenger3.setGender('F');
        passenger3.setUserType(Passenger.class);

        UserDTO passenger4 = new UserDTO();
        passenger4.setName("Elif");
        passenger4.setSurname("Arslan");
        passenger4.setEmail("elif@gmail.com");
        passenger4.setPhoneNumber("05007778899");
        passenger4.setPassword("elif123");
        passenger4.setGender('F');
        passenger4.setUserType(Passenger.class);
       
        userService.createUser(passenger1);
        userService.createUser(passenger2);
        userService.createUser(passenger3);
        userService.createUser(passenger4);
        
        
        
        //VEHİCLES---------------------------------------------------------------------------------
        // 🚌 Busses
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
        
        vehicleService.createVehicle(bus1);
        vehicleService.createVehicle(bus2);
        vehicleService.createVehicle(bus3);
        
        

        // ✈️ Planes
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
        
        vehicleService.createVehicle(plane1);
        vehicleService.createVehicle(plane2);
        vehicleService.createVehicle(plane3);
        
        
        
        
        //TRİPS---------------------------------------------------------------------------------------
        // BusTrips (past)
        TripDTO busTripDTO1 = new TripDTO();
        busTripDTO1.setDepartureStation("Antalya");
        busTripDTO1.setArrivalStation("İstanbul");
        busTripDTO1.setDepartureDate(LocalDateTime.of(2025, 6, 10, 10, 30));
        busTripDTO1.setTripTime(LocalTime.of(12, 0));
        busTripDTO1.setFare(500);
        busTripDTO1.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO1.setTripType(BusTrip.class);

        TripDTO busTripDTO2 = new TripDTO();
        busTripDTO2.setDepartureStation("Antalya");
        busTripDTO2.setArrivalStation("İstanbul");
        busTripDTO2.setDepartureDate(LocalDateTime.of(2023, 6, 10, 19, 0));
        busTripDTO2.setTripTime(LocalTime.of(4, 30));
        busTripDTO2.setFare(350);
        busTripDTO2.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO2.setTripType(BusTrip.class);
        
        
        //BusTrips (Future)
        TripDTO busTripDTO3 = new TripDTO();
        busTripDTO3.setDepartureStation("Adana");
        busTripDTO3.setArrivalStation("Ankara");
        busTripDTO3.setDepartureDate(LocalDateTime.of(2025, 6, 20, 8, 15));
        busTripDTO3.setTripTime(LocalTime.of(5, 0));
        busTripDTO3.setFare(280);
        busTripDTO3.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO3.setTripType(BusTrip.class);

        TripDTO busTripDTO4 = new TripDTO();
        busTripDTO4.setDepartureStation("Adana");
        busTripDTO4.setArrivalStation("Ankara");
        busTripDTO4.setDepartureDate(LocalDateTime.of(2021, 6, 20, 14, 45));
        busTripDTO4.setTripTime(LocalTime.of(3, 15));
        busTripDTO4.setFare(300);
        busTripDTO4.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO4.setTripType(BusTrip.class);
        
        TripDTO busTripDTO5 = new TripDTO();
        busTripDTO5.setDepartureStation("Adana");
        busTripDTO5.setArrivalStation("Ankara");
        busTripDTO5.setDepartureDate(LocalDateTime.of(2025, 6, 20, 10, 30));
        busTripDTO5.setTripTime(LocalTime.of(2, 30));
        busTripDTO5.setFare(200);
        busTripDTO5.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO5.setTripType(BusTrip.class);

        TripDTO busTripDTO6 = new TripDTO();
        busTripDTO6.setDepartureStation("Adana");
        busTripDTO6.setArrivalStation("Ankara");
        busTripDTO6.setDepartureDate(LocalDateTime.of(2025, 6, 20, 12, 00));
        busTripDTO6.setTripTime(LocalTime.of(2, 45));
        busTripDTO6.setFare(220);
        busTripDTO6.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO6.setTripType(BusTrip.class);
        
        TripDTO busTripDTO7 = new TripDTO();
        busTripDTO7.setDepartureStation("Adana");
        busTripDTO7.setArrivalStation("Ankara");
        busTripDTO7.setDepartureDate(LocalDateTime.of(2025, 6, 20, 13, 30));
        busTripDTO7.setTripTime(LocalTime.of(3, 30));
        busTripDTO7.setFare(240);
        busTripDTO7.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO7.setTripType(BusTrip.class);

        TripDTO busTripDTO8 = new TripDTO();
        busTripDTO8.setDepartureStation("Antalya");
        busTripDTO8.setArrivalStation("İstanbul");
        busTripDTO8.setDepartureDate(LocalDateTime.of(2025, 6, 20, 15, 00));
        busTripDTO8.setTripTime(LocalTime.of(2, 50));
        busTripDTO8.setFare(260);
        busTripDTO8.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO8.setTripType(BusTrip.class);

        TripDTO busTripDTO9 = new TripDTO();
        busTripDTO9.setDepartureStation("Adana");
        busTripDTO9.setArrivalStation("Ankara");
        busTripDTO9.setDepartureDate(LocalDateTime.of(2025, 6, 20, 17, 00));
        busTripDTO9.setTripTime(LocalTime.of(2, 40));
        busTripDTO9.setFare(280);
        busTripDTO9.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO9.setTripType(BusTrip.class);
        
        TripDTO busTripDTO10 = new TripDTO();
        busTripDTO10.setDepartureStation("Adana");
        busTripDTO10.setArrivalStation("Ankara");
        busTripDTO10.setDepartureDate(LocalDateTime.of(2025, 6, 20, 18, 30));
        busTripDTO10.setTripTime(LocalTime.of(3, 10));
        busTripDTO10.setFare(300);
        busTripDTO10.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO10.setTripType(BusTrip.class);

        Trip busTrip1 = tripService.createTrip(busTripDTO1);
        Trip busTrip2 = tripService.createTrip(busTripDTO2);
        Trip busTrip3 = tripService.createTrip(busTripDTO3);
        Trip busTrip4 = tripService.createTrip(busTripDTO4);
        Trip busTrip5 = tripService.createTrip(busTripDTO5);
        Trip busTrip6 = tripService.createTrip(busTripDTO6);
        Trip busTrip7 = tripService.createTrip(busTripDTO7);
        Trip busTrip8 = tripService.createTrip(busTripDTO8);
        Trip busTrip9 = tripService.createTrip(busTripDTO9);
        Trip busTrip10 = tripService.createTrip(busTripDTO10);
        
        
        
        //FlightTrips (Past)
        TripDTO flightTripDTO1 = new TripDTO();
        flightTripDTO1.setDepartureStation("Antalya");
        flightTripDTO1.setArrivalStation("İstanbul");
        flightTripDTO1.setDepartureDate(LocalDateTime.of(2025, 6, 10, 9, 0));
        flightTripDTO1.setTripTime(LocalTime.of(1, 0));
        flightTripDTO1.setFare(950);
        flightTripDTO1.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO1.setTripType(FlightTrip.class);

        TripDTO flightTripDTO2 = new TripDTO();
        flightTripDTO2.setDepartureStation("Antalya");
        flightTripDTO2.setArrivalStation("İstanbul");
        flightTripDTO2.setDepartureDate(LocalDateTime.of(2025, 6, 10, 11, 0));
        flightTripDTO2.setTripTime(LocalTime.of(1, 5));
        flightTripDTO2.setFare(970);
        flightTripDTO2.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTripDTO2.setTripType(FlightTrip.class);

        //FligtTrips (Future)
        TripDTO flightTripDTO3 = new TripDTO();
        flightTripDTO3.setDepartureStation("İstanbul");
        flightTripDTO3.setArrivalStation("İzmir");
        flightTripDTO3.setDepartureDate(LocalDateTime.of(2025, 6, 20, 13, 0));
        flightTripDTO3.setTripTime(LocalTime.of(1, 10));
        flightTripDTO3.setFare(990);
        flightTripDTO3.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTripDTO3.setTripType(FlightTrip.class);

        TripDTO flightTripDTO4 = new TripDTO();
        flightTripDTO4.setDepartureStation("Istanbul");
        flightTripDTO4.setArrivalStation("Izmir");
        flightTripDTO4.setDepartureDate(LocalDateTime.of(2025, 6, 20, 15, 0));
        flightTripDTO4.setTripTime(LocalTime.of(1, 0));
        flightTripDTO4.setFare(1010);
        flightTripDTO4.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO4.setTripType(FlightTrip.class);

        TripDTO flightTripDTO5 = new TripDTO();
        flightTripDTO5.setDepartureStation("İstanbul");
        flightTripDTO5.setArrivalStation("İzmir");
        flightTripDTO5.setDepartureDate(LocalDateTime.of(2025, 6, 20, 17, 0));
        flightTripDTO5.setTripTime(LocalTime.of(1, 15));
        flightTripDTO5.setFare(1030);
        flightTripDTO5.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTripDTO5.setTripType(FlightTrip.class);

        
        TripDTO flightTripDTO6 = new TripDTO();
        flightTripDTO6.setDepartureStation("İstanbul");
        flightTripDTO6.setArrivalStation("İzmir");
        flightTripDTO6.setDepartureDate(LocalDateTime.of(2025, 6, 20, 19, 0));
        flightTripDTO6.setTripTime(LocalTime.of(1, 10));
        flightTripDTO6.setFare(1050);
        flightTripDTO6.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTripDTO6.setTripType(FlightTrip.class);
        
        TripDTO flightTripDTO7 = new TripDTO();
        flightTripDTO7.setDepartureStation("İstanbul");
        flightTripDTO7.setArrivalStation("İzmir");
        flightTripDTO7.setDepartureDate(LocalDateTime.of(2025, 6, 20, 9, 0));
        flightTripDTO7.setTripTime(LocalTime.of(1, 0));
        flightTripDTO7.setFare(950);
        flightTripDTO7.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO7.setTripType(FlightTrip.class);

        TripDTO flightTripDTO8 = new TripDTO();
        flightTripDTO8.setDepartureStation("İstanbul");
        flightTripDTO8.setArrivalStation("İzmir");
        flightTripDTO8.setDepartureDate(LocalDateTime.of(2023, 6, 20, 16, 30));
        flightTripDTO8.setTripTime(LocalTime.of(1, 45));
        flightTripDTO8.setFare(1200);
        flightTripDTO8.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTripDTO8.setTripType(FlightTrip.class);

        TripDTO flightTripDTO9 = new TripDTO();
        flightTripDTO9.setDepartureStation("Antalya");
        flightTripDTO9.setArrivalStation("İstanbul");
        flightTripDTO9.setDepartureDate(LocalDateTime.of(2025, 6, 17, 11, 15));
        flightTripDTO9.setTripTime(LocalTime.of(1, 15));
        flightTripDTO9.setFare(1000);
        flightTripDTO9.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTripDTO9.setTripType(FlightTrip.class);

        TripDTO flightTripDTO10 = new TripDTO();
        flightTripDTO10.setDepartureStation("Diyarbakır");
        flightTripDTO10.setArrivalStation("İzmir");
        flightTripDTO10.setDepartureDate(LocalDateTime.of(2022, 6, 18, 15, 0));
        flightTripDTO10.setTripTime(LocalTime.of(2, 0));
        flightTripDTO10.setFare(1300);
        flightTripDTO10.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO10.setTripType(FlightTrip.class);

        
        Trip flightTrip1 = tripService.createTrip(flightTripDTO1);
        Trip flightTrip2 = tripService.createTrip(flightTripDTO2);
        Trip flightTrip3 = tripService.createTrip(flightTripDTO3);
        Trip flightTrip4 = tripService.createTrip(flightTripDTO4);
        Trip flightTrip5 = tripService.createTrip(flightTripDTO5);
        Trip flightTrip6 = tripService.createTrip(flightTripDTO6);
        Trip flightTrip7 = tripService.createTrip(flightTripDTO7);
        Trip flightTrip8 = tripService.createTrip(flightTripDTO8);
        Trip flightTrip9 = tripService.createTrip(flightTripDTO9);
        Trip flightTrip10 = tripService.createTrip(flightTripDTO10);
        
        
        //Reservations
        Passenger passengerTuna = (Passenger)userService.getUserByEmail("tuna@gmail.com");
        Passenger passengerElif = (Passenger)userService.getUserByEmail("elif@gmail.com");
        
        ReservationDTO reservationDTO1 = new ReservationDTO();
        reservationDTO1.setPassenger(passengerTuna);
        reservationDTO1.setFare(busTripDTO1.getFare());
        reservationDTO1.setReservationType(BusReservation.class);
        reservationDTO1.setTrip(busTrip1);
        reservationDTO1.setSeat(busTrip1.getVehicle().getSeatList().get(1));
        
        ReservationDTO reservationDTO2 = new ReservationDTO();
        reservationDTO2.setPassenger(passengerTuna);
        reservationDTO2.setFare(busTripDTO3.getFare());
        reservationDTO2.setReservationType(BusReservation.class);
        reservationDTO2.setTrip(busTrip3);
        reservationDTO2.setSeat(busTrip3.getVehicle().getSeatList().get(2));
        
        ReservationDTO reservationDTO3 = new ReservationDTO();
        reservationDTO3.setPassenger(passengerTuna);
        reservationDTO3.setFare(busTripDTO3.getFare());
        reservationDTO3.setReservationType(BusReservation.class);
        reservationDTO3.setTrip(busTrip3);
        reservationDTO3.setSeat(busTrip3.getVehicle().getSeatList().get(6));
        
        ReservationDTO reservationDTO4 = new ReservationDTO();
        reservationDTO4.setPassenger(passengerTuna);
        reservationDTO4.setFare(busTripDTO3.getFare());
        reservationDTO4.setReservationType(BusReservation.class);
        reservationDTO4.setTrip(busTrip3);
        reservationDTO4.setSeat(busTrip3.getVehicle().getSeatList().get(8));
        
        ReservationDTO reservationDTO5 = new ReservationDTO();
        reservationDTO5.setPassenger(passengerTuna);
        reservationDTO5.setFare(busTripDTO3.getFare());
        reservationDTO5.setReservationType(BusReservation.class);
        reservationDTO5.setTrip(busTrip3);
        reservationDTO5.setSeat(busTrip3.getVehicle().getSeatList().get(12));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Flight rezervasyon
        
        ReservationDTO reservationDTO6 = new ReservationDTO();
        reservationDTO6.setPassenger(passengerElif);
        reservationDTO6.setFare(flightTripDTO3.getFare());
        reservationDTO6.setReservationType(FlightReservation.class);
        reservationDTO6.setTrip(flightTrip3);
        reservationDTO6.setSeat(flightTrip3.getVehicle().getSeatList().get(6));
        
        ReservationDTO reservationDTO7 = new ReservationDTO();
        reservationDTO7.setPassenger(passengerElif);
        reservationDTO7.setFare(flightTripDTO3.getFare());
        reservationDTO7.setReservationType(FlightReservation.class);
        reservationDTO7.setTrip(flightTrip3);
        reservationDTO7.setSeat(flightTrip3.getVehicle().getSeatList().get(7));
        
        ReservationDTO reservationDTO8 = new ReservationDTO();
        reservationDTO8.setPassenger(passengerElif);
        reservationDTO8.setFare(flightTripDTO3.getFare());
        reservationDTO8.setReservationType(FlightReservation.class);
        reservationDTO8.setTrip(flightTrip3);
        reservationDTO8.setSeat(flightTrip3.getVehicle().getSeatList().get(8));
        
        ReservationDTO reservationDTO9 = new ReservationDTO();
        reservationDTO9.setPassenger(passengerElif);
        reservationDTO9.setFare(flightTripDTO3.getFare());
        reservationDTO9.setReservationType(FlightReservation.class);
        reservationDTO9.setTrip(flightTrip3);
        reservationDTO9.setSeat(flightTrip3.getVehicle().getSeatList().get(9));
        
        ReservationDTO reservationDTO10 = new ReservationDTO();
        reservationDTO10.setPassenger(passengerElif);
        reservationDTO10.setFare(flightTripDTO3.getFare());
        reservationDTO10.setReservationType(FlightReservation.class);
        reservationDTO10.setTrip(flightTrip3);
        reservationDTO10.setSeat(flightTrip3.getVehicle().getSeatList().get(10));
        
        reservationService.createReservation(passengerTuna, reservationDTO1);
        reservationService.createReservation(passengerTuna, reservationDTO2);
        reservationService.createReservation(passengerTuna, reservationDTO3);
        reservationService.createReservation(passengerTuna, reservationDTO4);
        reservationService.createReservation(passengerTuna, reservationDTO5);
        reservationService.createReservation(passengerElif, reservationDTO6);
        reservationService.createReservation(passengerElif, reservationDTO7);
        reservationService.createReservation(passengerElif, reservationDTO8);
        reservationService.createReservation(passengerElif, reservationDTO9);
        reservationService.createReservation(passengerElif, reservationDTO10);
        


        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
