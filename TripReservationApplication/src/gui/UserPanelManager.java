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
        
        //Panels added to contentPanel based on user type
        //Other panels used will be added to the contentPanel where necessary.
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

        // The first panel to be displayed after login
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

        //Items to be displayed in Menu according to user type
        if (currentUser instanceof Admin) {
            adminPanelItem = new JMenuItem("Admin Panel");
        } 
        else if (currentUser instanceof Passenger) {
            searchTripItem = new JMenuItem("Search Trips");
            pastTripsItem = new JMenuItem("My Trips");
            accountItem = new JMenuItem("My Account");
        }

        //Non-null items are added to the menu.
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

        // ActionListeners
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
    
    //The visibility of the MenuBar is changed. (For example, it will not be visible in TripsPanel)
    public void setMenuBarVisible(boolean visible) {
        menuBar.setVisible(visible);
    }
    
    //To prevent the backspace button from causing an error, panels are added to the contentPanel here when adding them from other classes.
    public void addPanel(String key, JPanel panel) {
        contentPanel.add(panel, key);
    }
    
    //We added it so that the panel can be accessed from outside as well.
    public void showPanelByKey(String key) {
        cardLayout.show(contentPanel, key);
    }
}
