package gui;

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
        //cardLayout: JPanel içinde birden fazla paneli aynı alanda göstermek için kullanılır ama aynı anda sadece bir tanesi görünür.
        //contentPanel: Paneller burada yer alacak, cardLayout yonetecek
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Kullanıcı türüne göre içerikler
        switch (user) {
            case Admin admin -> {
                contentPanel.add(new AdminPanel(admin), "admin");
                contentPanel.add(new SearchTripPanel(admin), "searching");
                contentPanel.add(new AccountPanel(admin), "account");
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

        // İlk gösterilecek panel
        cardLayout.show(contentPanel, user instanceof Admin ? "admin" : "searching");
    }
//-------------------------------------------End of constructor method-----------------------------

    
    private JMenuBar createCustomMenuBar() {
        JMenuBar menuBar = new JMenuBar();
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

        if (currentUser instanceof Admin) {
            adminPanelItem = new JMenuItem("Admin Panel");
        } 
        else if (currentUser instanceof Passenger) {
            searchTripItem = new JMenuItem("Search Trips");
            pastTripsItem = new JMenuItem("My Trips");
            accountItem = new JMenuItem("My Account");
        }

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

    //Dışarıdan panel geçişi için ekledik
    public void showPanelByKey(String key) {
        cardLayout.show(contentPanel, key);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
    
    public void setMenuBarVisible(boolean visible) {
        menuBar.setVisible(visible);
    }
    
    public void addPanel(String key, JPanel panel) {
        contentPanel.add(panel, key);
    }
}
