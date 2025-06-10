
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
        TripDTO busTripDTO1 = new TripDTO();
        busTripDTO1.setDepartureStation("Adana");
        busTripDTO1.setArrivalStation("Ankara");
        busTripDTO1.setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 30));
        busTripDTO1.setTripTime(LocalTime.of(12, 0));
        busTripDTO1.setFare(500);
        busTripDTO1.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO1.setTripType(BusTrip.class);

        TripDTO busTripDTO2 = new TripDTO();
        busTripDTO2.setDepartureStation("Izmir");
        busTripDTO2.setArrivalStation("Bursa");
        busTripDTO2.setDepartureDate(LocalDateTime.of(2023, 6, 16, 9, 0));
        busTripDTO2.setTripTime(LocalTime.of(4, 30));
        busTripDTO2.setFare(350);
        busTripDTO2.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO2.setTripType(BusTrip.class);

        TripDTO busTripDTO3 = new TripDTO();
        busTripDTO3.setDepartureStation("Antalya");
        busTripDTO3.setArrivalStation("Konya");
        busTripDTO3.setDepartureDate(LocalDateTime.of(2025, 6, 17, 8, 15));
        busTripDTO3.setTripTime(LocalTime.of(5, 0));
        busTripDTO3.setFare(280);
        busTripDTO3.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO3.setTripType(BusTrip.class);

        TripDTO busTripDTO4 = new TripDTO();
        busTripDTO4.setDepartureStation("Adana");
        busTripDTO4.setArrivalStation("Gaziantep");
        busTripDTO4.setDepartureDate(LocalDateTime.of(2021, 6, 18, 14, 45));
        busTripDTO4.setTripTime(LocalTime.of(3, 15));
        busTripDTO4.setFare(300);
        busTripDTO4.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO4.setTripType(BusTrip.class);
        
        TripDTO busTripDTO5 = new TripDTO();
        busTripDTO5.setDepartureStation("Adana");
        busTripDTO5.setArrivalStation("Ankara");
        busTripDTO5.setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 30));
        busTripDTO5.setTripTime(LocalTime.of(2, 30));
        busTripDTO5.setFare(200);
        busTripDTO5.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO5.setTripType(BusTrip.class);

        TripDTO busTripDTO6 = new TripDTO();
        busTripDTO6.setDepartureStation("Adana");
        busTripDTO6.setArrivalStation("Ankara");
        busTripDTO6.setDepartureDate(LocalDateTime.of(2025, 6, 15, 12, 00));
        busTripDTO6.setTripTime(LocalTime.of(2, 45));
        busTripDTO6.setFare(220);
        busTripDTO6.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO6.setTripType(BusTrip.class);
        
        
        
        TripDTO busTripDTO7 = new TripDTO();
        busTripDTO7.setDepartureStation("Adana");
        busTripDTO7.setArrivalStation("Ankara");
        busTripDTO7.setDepartureDate(LocalDateTime.of(2025, 6, 15, 13, 30));
        busTripDTO7.setTripTime(LocalTime.of(3, 30));
        busTripDTO7.setFare(240);
        busTripDTO7.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO7.setTripType(BusTrip.class);

        TripDTO busTripDTO8 = new TripDTO();
        busTripDTO8.setDepartureStation("Adana");
        busTripDTO8.setArrivalStation("Ankara");
        busTripDTO8.setDepartureDate(LocalDateTime.of(2025, 6, 15, 15, 00));
        busTripDTO8.setTripTime(LocalTime.of(2, 50));
        busTripDTO8.setFare(260);
        busTripDTO8.setVehicle(vehicleService.getVehicleByIdentifier("34 AA 1313"));
        busTripDTO8.setTripType(BusTrip.class);

        TripDTO busTripDTO9 = new TripDTO();
        busTripDTO9.setDepartureStation("Adana");
        busTripDTO9.setArrivalStation("Ankara");
        busTripDTO9.setDepartureDate(LocalDateTime.of(2025, 6, 15, 17, 00));
        busTripDTO9.setTripTime(LocalTime.of(2, 40));
        busTripDTO9.setFare(280);
        busTripDTO9.setVehicle(vehicleService.getVehicleByIdentifier("35 BB 2525"));
        busTripDTO9.setTripType(BusTrip.class);
        
        
        TripDTO busTripDTO10 = new TripDTO();
        busTripDTO10.setDepartureStation("Adana");
        busTripDTO10.setArrivalStation("Ankara");
        busTripDTO10.setDepartureDate(LocalDateTime.of(2025, 6, 15, 18, 30));
        busTripDTO10.setTripTime(LocalTime.of(3, 10));
        busTripDTO10.setFare(300);
        busTripDTO10.setVehicle(vehicleService.getVehicleByIdentifier("06 CC 3636"));
        busTripDTO10.setTripType(BusTrip.class);

        
        
        
        //FlightTrips
        
        TripDTO flightTripDTO1 = new TripDTO();
        flightTripDTO1.setDepartureStation("İstanbul");
        flightTripDTO1.setArrivalStation("İzmir");
        flightTripDTO1.setDepartureDate(LocalDateTime.of(2025, 6, 15, 9, 0));
        flightTripDTO1.setTripTime(LocalTime.of(1, 0));
        flightTripDTO1.setFare(950);
        flightTripDTO1.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO1.setTripType(FlightTrip.class);

        TripDTO flightTripDTO2 = new TripDTO();
        flightTripDTO2.setDepartureStation("İstanbul");
        flightTripDTO2.setArrivalStation("İzmir");
        flightTripDTO2.setDepartureDate(LocalDateTime.of(2025, 6, 15, 11, 0));
        flightTripDTO2.setTripTime(LocalTime.of(1, 5));
        flightTripDTO2.setFare(970);
        flightTripDTO2.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTripDTO2.setTripType(FlightTrip.class);

        TripDTO flightTripDTO3 = new TripDTO();
        flightTripDTO3.setDepartureStation("İstanbul");
        flightTripDTO3.setArrivalStation("İzmir");
        flightTripDTO3.setDepartureDate(LocalDateTime.of(2025, 6, 15, 13, 0));
        flightTripDTO3.setTripTime(LocalTime.of(1, 10));
        flightTripDTO3.setFare(990);
        flightTripDTO3.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTripDTO3.setTripType(FlightTrip.class);

        TripDTO flightTripDTO4 = new TripDTO();
        flightTripDTO4.setDepartureStation("Istanbul");
        flightTripDTO4.setArrivalStation("Izmir");
        flightTripDTO4.setDepartureDate(LocalDateTime.of(2025, 6, 15, 15, 0));
        flightTripDTO4.setTripTime(LocalTime.of(1, 0));
        flightTripDTO4.setFare(1010);
        flightTripDTO4.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO4.setTripType(FlightTrip.class);

        TripDTO flightTripDTO5 = new TripDTO();
        flightTripDTO5.setDepartureStation("İstanbul");
        flightTripDTO5.setArrivalStation("İzmir");
        flightTripDTO5.setDepartureDate(LocalDateTime.of(2025, 6, 15, 17, 0));
        flightTripDTO5.setTripTime(LocalTime.of(1, 15));
        flightTripDTO5.setFare(1030);
        flightTripDTO5.setVehicle(vehicleService.getVehicleByIdentifier("PC202"));
        flightTripDTO5.setTripType(FlightTrip.class);

        
        TripDTO flightTripDTO6 = new TripDTO();
        flightTripDTO6.setDepartureStation("İstanbul");
        flightTripDTO6.setArrivalStation("İzmir");
        flightTripDTO6.setDepartureDate(LocalDateTime.of(2025, 6, 15, 19, 0));
        flightTripDTO6.setTripTime(LocalTime.of(1, 10));
        flightTripDTO6.setFare(1050);
        flightTripDTO6.setVehicle(vehicleService.getVehicleByIdentifier("THY303"));
        flightTripDTO6.setTripType(FlightTrip.class);
        
        TripDTO flightTripDTO7 = new TripDTO();
        flightTripDTO7.setDepartureStation("İstanbul");
        flightTripDTO7.setArrivalStation("İzmir");
        flightTripDTO7.setDepartureDate(LocalDateTime.of(2025, 6, 15, 9, 0));
        flightTripDTO7.setTripTime(LocalTime.of(1, 0));
        flightTripDTO7.setFare(950);
        flightTripDTO7.setVehicle(vehicleService.getVehicleByIdentifier("TK101"));
        flightTripDTO7.setTripType(FlightTrip.class);

        TripDTO flightTripDTO8 = new TripDTO();
        flightTripDTO8.setDepartureStation("Ankara");
        flightTripDTO8.setArrivalStation("Van");
        flightTripDTO8.setDepartureDate(LocalDateTime.of(2023, 6, 16, 16, 30));
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
        Passenger passengerDikdas = (Passenger)userService.getUserByEmail("dikdas@gmail.com");
        Passenger passengerFatih = (Passenger) userService.getUserByEmail("fatih@gmail.com");
        
        ReservationDTO reservationDTO1 = new ReservationDTO();
        reservationDTO1.setPassenger(passengerDikdas);
        reservationDTO1.setFare(busTripDTO1.getFare());
        reservationDTO1.setReservationType(BusReservation.class);
        reservationDTO1.setTrip(busTrip1);
        reservationDTO1.setSeat(busTrip1.getVehicle().getSeatList().get(1));
        
        
        ReservationDTO reservationDTO2 = new ReservationDTO();
        reservationDTO2.setPassenger(passengerDikdas);
        reservationDTO2.setFare(busTripDTO1.getFare());
        reservationDTO2.setReservationType(BusReservation.class);
        reservationDTO2.setTrip(busTrip1);
        reservationDTO2.setSeat(busTrip1.getVehicle().getSeatList().get(2));
        
        ReservationDTO reservationDTO3 = new ReservationDTO();
        reservationDTO3.setPassenger(passengerDikdas);
        reservationDTO3.setFare(busTripDTO1.getFare());
        reservationDTO3.setReservationType(BusReservation.class);
        reservationDTO3.setTrip(busTrip1);
        reservationDTO3.setSeat(busTrip1.getVehicle().getSeatList().get(3));
        
        ReservationDTO reservationDTO4 = new ReservationDTO();
        reservationDTO4.setPassenger(passengerDikdas);
        reservationDTO4.setFare(busTripDTO1.getFare());
        reservationDTO4.setReservationType(BusReservation.class);
        reservationDTO4.setTrip(busTrip1);
        reservationDTO4.setSeat(busTrip1.getVehicle().getSeatList().get(4));
        
        ReservationDTO reservationDTO5 = new ReservationDTO();
        reservationDTO5.setPassenger(passengerDikdas);
        reservationDTO5.setFare(busTripDTO1.getFare());
        reservationDTO5.setReservationType(BusReservation.class);
        reservationDTO5.setTrip(busTrip1);
        reservationDTO5.setSeat(busTrip1.getVehicle().getSeatList().get(5));
        
        ReservationDTO reservationDTO6 = new ReservationDTO();
        reservationDTO6.setPassenger(passengerDikdas);
        reservationDTO6.setFare(flightTripDTO1.getFare());
        reservationDTO6.setReservationType(FlightReservation.class);
        reservationDTO6.setTrip(flightTrip1);
        reservationDTO6.setSeat(flightTrip1.getVehicle().getSeatList().get(6));
        
        ReservationDTO reservationDTO7 = new ReservationDTO();
        reservationDTO7.setPassenger(passengerDikdas);
        reservationDTO7.setFare(flightTripDTO1.getFare());
        reservationDTO7.setReservationType(FlightReservation.class);
        reservationDTO7.setTrip(flightTrip1);
        reservationDTO7.setSeat(flightTrip1.getVehicle().getSeatList().get(7));
        
        ReservationDTO reservationDTO8 = new ReservationDTO();
        reservationDTO8.setPassenger(passengerDikdas);
        reservationDTO8.setFare(flightTripDTO1.getFare());
        reservationDTO8.setReservationType(FlightReservation.class);
        reservationDTO8.setTrip(flightTrip1);
        reservationDTO8.setSeat(flightTrip1.getVehicle().getSeatList().get(8));
        
        ReservationDTO reservationDTO9 = new ReservationDTO();
        reservationDTO9.setPassenger(passengerDikdas);
        reservationDTO9.setFare(flightTripDTO1.getFare());
        reservationDTO9.setReservationType(FlightReservation.class);
        reservationDTO9.setTrip(flightTrip1);
        reservationDTO9.setSeat(flightTrip1.getVehicle().getSeatList().get(9));
        
        ReservationDTO reservationDTO10 = new ReservationDTO();
        reservationDTO10.setPassenger(passengerDikdas);
        reservationDTO10.setFare(flightTripDTO1.getFare());
        reservationDTO10.setReservationType(FlightReservation.class);
        reservationDTO10.setTrip(flightTrip1);
        reservationDTO10.setSeat(flightTrip1.getVehicle().getSeatList().get(10));
        
        reservationService.createReservation(passengerDikdas, reservationDTO1);
        reservationService.createReservation(passengerDikdas, reservationDTO2);
        reservationService.createReservation(passengerDikdas, reservationDTO3);
        reservationService.createReservation(passengerDikdas, reservationDTO4);
        reservationService.createReservation(passengerDikdas, reservationDTO5);
        reservationService.createReservation(passengerFatih, reservationDTO6);
        reservationService.createReservation(passengerFatih, reservationDTO7);
        reservationService.createReservation(passengerFatih, reservationDTO8);
        reservationService.createReservation(passengerFatih, reservationDTO9);
        reservationService.createReservation(passengerFatih, reservationDTO10);
        


        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
