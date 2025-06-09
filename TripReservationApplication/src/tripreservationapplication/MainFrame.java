package tripreservationapplication;

import dto.TripDTO;
import gui.LoginRegisterPanel;
import gui.UserPanelManager;   
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.*;
import trip.model.BusTrip;
import trip.model.Trip;
import trip.service.TripService;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;       
import user.repository.UserRepository;
import vehicle.model.Bus;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    
    public MainFrame() {
        instance = this;  //Singleton
        setTitle("Trip Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900 , 600);
        setLocationRelativeTo(null);

        ornekOlustur();
//        tripOrnegiOlustur();
  
        //Başlangıçta login paneli göster, menü bar yok
        setJMenuBar(null);
        setContentPane(new LoginRegisterPanel());

        setVisible(true);
    } 
//-------------------------------------------End of constructor method-----------------------------

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
    
    //Giriş başarılı olunca çağrılacak metod (LoginRegisterPanel'de Login metodu cagıracak). 
    //Boylece giris yapan kullanıcıya gore paneller ve MenuBar duzenlenecek.
    public void showUserPanelManager(User user) {
        UserPanelManager userPanel = new UserPanelManager(user); 
        setJMenuBar(userPanel.getMenuBar());
        setContentPane(userPanel);
        revalidate();
        repaint();
    }

    
    
    
    
    
    
    
    
    
 
    
    
    
    
    
    
    
    
    public void ornekOlustur(){
        
        //DENEME AMACLI NESNELER BURADAN OLUSTURULUYOR. SONRA SİL
        //Passenger panel deneme amaclı yapılmıstır. sonra sil
        User user = new Passenger("aa","bb","1","1","6765456782",'M',null);
        UserRepository rep = UserRepository.getInstance();
        rep.addUser(user);
        
        User admin = new Admin("s","b","admin@gmail.com","122","07654325676",'F');
        rep.addUser(admin);
        
        User admin2 = new Admin("s","b","2","2","07654325676",'F');
        rep.addUser(admin2);
        
        
        
        //TRİP EKLEME DENEME AMACLI
//        TripService tripService = new TripService();
//        Bus bus = new Bus(40,"dd");
//        Trip trip = new BusTrip.BusTripBuilder()
//                .setBus(bus)
//                .setDepartureStation("Adana")
//                .setArrivalStation("Ankara")
//                .setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 0))   //15 Haziran 2025, saat 10:00
//                .setTripTime(LocalTime.of(22, 0))
//                .setFare(2200.00)
//                .build();     
//        tripService.createTrip(trip);
        
        TripService tripService = new TripService();
         Bus bus = new Bus(40,"dd");
        TripDTO dto = new TripDTO();
        dto.setVehicle(bus);
        dto.setDepartureStation("Adana");
        dto.setArrivalStation("Ankara");
        dto.setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 0));   //15 Haziran 2025, saat 10:00
        dto.setTripTime(LocalTime.of(22, 0));
        dto.setFare(2200.00);
        dto.setTripType(BusTrip.class);
        tripService.createTrip(dto);
        
        //RESERVATİON EKLEME DENEME AMACLI
//        ReservationService reservationService = new ReservationService();
//        ReservationFactory factory = new ReservationFactory();
//        Reservation reservation = factory.createReservation((Passenger)user,100, trip.getVehicle().getSeatList().get(1), trip);
//        reservationService.createReservation(user, reservation);
//        trip.getVehicle().getSeatList().get(1).setIsBooked(true);
//        trip.getReservations();
          
        
    }

    
    public void tripOrnegiOlustur(){
        
        User passenger = new Passenger("aa","bb","11","1","6765456782",'M',null);
        UserRepository rep1 = UserRepository.getInstance();
        rep1.addUser(passenger);
        
        
        TripService tripService = new TripService();
        Bus bus = new Bus(40,"dd");
        Trip trip1 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
        Trip trip2 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 7, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
        Trip trip3 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 3, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
        Trip trip4 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 2, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
        Trip trip5 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 1, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
        Trip trip6 = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();     
//        tripService.createTrip(trip1);
//        tripService.createTrip(trip2);
//        tripService.createTrip(trip3);
//        tripService.createTrip(trip4);
//        tripService.createTrip(trip5);
//        tripService.createTrip(trip6);
        
    }
}
