package tripreservationapplication;

import gui.AccountPanel;   //SİLLL!!!!
import gui.AdminPanel;
import gui.LoginRegisterPanel;
import gui.UserPanelManager;    //SİL!!!!!
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.*;
import trip.model.BusTrip;
import trip.model.Trip;
import trip.service.TripService;
import user.model.Admin;
import user.model.Passenger;  //SİL!!!!!
import user.model.User;       //SİL!!!!!
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

        
        //DENEME AMACLI NESNELER BURADAN OLUSTURULUYOR. SONRA SİL
        //Passenger panel deneme amaclı yapılmıstır. sonra sil
        User user = new Passenger("aa","bb","dds@gmail.com","122","6765456782",'M',null);
        UserRepository rep = UserRepository.getInstance();
        rep.addUser(user);
        
        User admin = new Admin("s","b","admin@gmail.com","122","07654325676",'F');
        rep.addUser(admin);
        
        Bus bus = new Bus(12,"dd");
        
        Trip trip = new BusTrip.BusTripBuilder()
                .setBus(bus)
                .setDepartureStation("Adana")
                .setArrivalStation("Ankara")
                .setDepartureDate(LocalDateTime.of(2025, 6, 15, 10, 0))   //15 Haziran 2025, saat 10:00
                .setTripTime(LocalTime.of(22, 0))
                .setFare(2200.00)
                .build();
        
        TripService tripService = new TripService();
        tripService.createTrip(trip);
        
 
       
        
        //Başlangıçta login paneli göster, menü bar yok
//        setJMenuBar(null);
//        setContentPane(new LoginRegisterPanel());
//
//        setVisible(true);
        
        
       add(new UserPanelManager(user));
    }
    
    //Giriş başarılı olunca çağrılacak metod (LoginRegisterPanel'de Login metodu cagıracak). 
    //Boylece giris yapan kullanıcıya gore paneller ve MenuBar duzenlenecek.
    public void showUserPanelManager(User user) {
        UserPanelManager userPanel = new UserPanelManager(user); 
        setJMenuBar(userPanel.getMenuBar()); // Menü bar görünür olacak
        setContentPane(userPanel);
        revalidate();
        repaint();
    }
    
    public static MainFrame getInstance() {
        return instance;
    }
}
