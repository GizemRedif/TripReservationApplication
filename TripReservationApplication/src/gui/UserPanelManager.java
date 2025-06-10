package gui;

import gui.passengerPanels.MyTripsPanel;
import gui.adminPanels.AdminMainPanel;
import javax.swing.*;
import java.awt.*;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;


public class UserPanelManager extends JPanel {
    private CardLayout cardLayout; 
    private JPanel contentPanel;
    private User currentUser;
    private JMenuBar menuBar;

    public UserPanelManager(User user) {
        setLayout(new BorderLayout());
        currentUser = user;

        // Menü bar
        menuBar = createCustomMenuBar();
        add(menuBar, BorderLayout.NORTH);

        // CardLayout panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        //Kullanıcı turune gore contentPanel'e eklenen paneller
        //Kullanılan diger paneller contentPanel'e gerekli yerlerde eklenecek.
        switch (user) {
            case Admin admin -> {
                contentPanel.add(new AdminMainPanel(admin), "admin");
            }
            case Passenger passenger -> {
                contentPanel.add(new SearchTripPanel(passenger), "searching");
                contentPanel.add(new MyTripsPanel(passenger), "myTrips");
                contentPanel.add(new AccountPanel(passenger), "account");
            }
            default -> {
            }
        }

        add(contentPanel, BorderLayout.CENTER);

        // Login isleminden sonra ilk gösterilecek panel
        cardLayout.show(contentPanel, user instanceof Admin ? "admin" : "searching");
    }
//-------------------------------------------End of constructor method-----------------------------

    
    private JMenuBar createCustomMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(400, 40));
        menuBar.setBackground(new Color(19, 29, 79));

        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);
        menu.setOpaque(true);
        menu.setBackground(new Color(19, 29, 79));

        JMenuItem adminPanelItem = null;
        JMenuItem searchTripItem = null;
        JMenuItem accountItem = null;
        JMenuItem pastTripsItem = null;

        //Kullanıcı turune gore Menu'de gosterilecek itemler
        if (currentUser instanceof Admin) {
            adminPanelItem = new JMenuItem("Admin Panel");
        } 
        else if (currentUser instanceof Passenger) {
            searchTripItem = new JMenuItem("Search Trips");
            pastTripsItem = new JMenuItem("My Trips");
            accountItem = new JMenuItem("My Account");
        }

        //null olmayan itemler menu'ye ekleniyor.
        JMenuItem[] items = { searchTripItem, pastTripsItem, accountItem, adminPanelItem };
        for (JMenuItem item : items) {
            if (item != null) {
                item.setBackground(new Color(19, 29, 79));
                item.setForeground(Color.WHITE);
                item.setFont(new Font("Arial", Font.PLAIN, 14));
                menu.add(item);
            }
        }
        menuBar.add(menu);

        // ActionListener'lar
        if (searchTripItem != null)
            searchTripItem.addActionListener(e -> showPanelByKey("searching"));
        if (accountItem != null)
            accountItem.addActionListener(e -> showPanelByKey("account"));
        if (pastTripsItem != null)
            pastTripsItem.addActionListener(e -> showPanelByKey("myTrips"));
        if (adminPanelItem != null)
            adminPanelItem.addActionListener(e -> showPanelByKey("admin"));

        return menuBar;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
    
    //MenuBar'ın gorunurlugu degistirilir. (Ornegin TripsPanel'de gorunmeyecek)
    public void setMenuBarVisible(boolean visible) {
        menuBar.setVisible(visible);
    }
    
    //Geri tusu hata cıkarmasın diye paneller baska sınıflardan eklenirken de buradaki contentPanel'e eklenir.
    public void addPanel(String key, JPanel panel) {
        contentPanel.add(panel, key);
    }
    
    //Dışarıdan da panel geçişi olabilmesi için ekledik. 
    public void showPanelByKey(String key) {
        cardLayout.show(contentPanel, key);
    }
}
