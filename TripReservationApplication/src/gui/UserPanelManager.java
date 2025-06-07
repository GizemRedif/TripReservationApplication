package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import user.model.Admin;
import user.model.Passenger;

import user.model.User;





//HATA YA DA EKSİK YOKSA TAMAMLANDI







public class UserPanelManager extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private User currentUser; // sınıf değişkeni
    private JMenuBar menuBar; //MenuBar login ekranında gorunmeyecek, diger her ekranda gorunecek. bunu MainFrame icerisinde ayarlamak icin kullanılacak 


    public UserPanelManager(User user) {
        setLayout(new BorderLayout());
        currentUser = user;
        
        // 🔹 Özelleştirilmiş Menü Bar ekleniyor
        menuBar = createCustomMenuBar();
        add(menuBar, BorderLayout.NORTH);

        // 🔹 Content panel (CardLayout)
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        //User'in turune gore menuBar olusturuluyor ve hangi panele erisebilecekleri belirleniyor.
        if(user instanceof Admin){ 
            contentPanel.add(new AdminPanel((Admin) user), "admin");
            contentPanel.add(new SearchTripPanel((Admin) user), "searching");
            contentPanel.add(new AccountPanel((Admin) user), "account");
        }        
        else{
            contentPanel.add(new SearchTripPanel((Passenger) user), "searching");
            contentPanel.add(new PastTripsPanel((Passenger) user), "pastTrips");
            contentPanel.add(new AccountPanel((Passenger) user), "account");
        }

        add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, user instanceof Admin ? "admin" : "searching"); // User turune gore gosterilecek ilk paneller belirleniyor

    }

    private JMenuBar createCustomMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(400, 40)); // Menü bar yüksekliği büyütüldü
        menuBar.setBackground(new Color(19, 29, 79));

        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);
        menu.setOpaque(true);
        menu.setBackground(new Color(19, 29, 79));

        JMenuItem adminPanelItem = null;
        JMenuItem searchTripItem = null;
        JMenuItem accountItem = null;
        JMenuItem pastTripsItem = null;

        
        //User turune gore menubar icerikleri belirleniyor.
        if(currentUser instanceof Admin){ 
            adminPanelItem = new JMenuItem("Admin Panel"); 
            searchTripItem = new JMenuItem("Search Trips");
            accountItem = new JMenuItem("My Account");
        }        
        else if (currentUser instanceof Passenger){
            searchTripItem = new JMenuItem("Search Trips");
            pastTripsItem = new JMenuItem("MyPast Trips");
            accountItem = new JMenuItem("My Account");
        }
       
        JMenuItem[] items = {searchTripItem, pastTripsItem, accountItem, adminPanelItem};
        //Null olmayan iremlerin stili tanımlanır
        for (JMenuItem item : items) {
            if (item != null) {
                item.setBackground(new Color(19, 29, 79));
                item.setForeground(Color.WHITE);
                item.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }
        //Null olmayan itemler menu'ye eklenir
        for (JMenuItem item : items) {
            if (item != null) {
                menu.add(item);
            }
        }

        menuBar.add(menu); // BU SATIR OLMAZSA GÖRÜNMEZ

        //Menü olayları (Itemlere tıklanınca hangi panellerin acılacagı)
        searchTripItem.addActionListener(e -> cardLayout.show(contentPanel, "searching"));
        accountItem.addActionListener(e -> cardLayout.show(contentPanel, "account"));         
        if (pastTripsItem != null)
            pastTripsItem.addActionListener(e -> cardLayout.show(contentPanel, "pastTrips"));
        if (adminPanelItem != null)
            adminPanelItem.addActionListener(e -> cardLayout.show(contentPanel, "admin"));


        return menuBar;
    }

    public JMenuBar getMenuBar() { //MainFrame icinde cagırılacak
        return menuBar;
    }

}
