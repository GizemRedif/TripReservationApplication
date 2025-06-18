package gui.adminPanels;

import gui.AccountPanel;
import gui.SearchTripPanel;
import gui.UserPanelManager;
import user.model.Admin;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import static gui.components.StyleButtons.createStyledBlueButton;

//The first panel shown when the admin logs in
public class AdminMainPanel extends JPanel {

    JLabel welcomeLabel; 
    
    public AdminMainPanel(Admin admin) {
        setLayout(new GridLayout(1, 2));  // Left and right are equal width
        setBackground(new Color(239, 228, 210)); // Panel background

        // Left Panel – "Welcome" 
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(239, 228, 210));
        leftPanel.setLayout(new GridBagLayout());
        welcomeLabel = new JLabel("Welcome Admin!");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(welcomeLabel);

        // Right Panel – Butons
        JPanel rightPanel = new JPanel(new GridLayout(6, 1, 10, 10)); //6 buttons 
        rightPanel.setBackground(new Color(239, 228, 210));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Buttons (created with the static method in the StyleButton class)
        JButton editTripBtn = createStyledBlueButton("Edit Trip/Reservation");
        JButton addTripBtn = createStyledBlueButton("Trip Add");
        JButton addVehicleBtn = createStyledBlueButton("Vehicle Add");
        JButton addUserBtn = createStyledBlueButton("User Add");
        JButton editUserBtn = createStyledBlueButton("Edit User");
        JButton accountBtn = createStyledBlueButton("My Account");

        // ActionListeners
        editTripBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("searching", new SearchTripPanel(admin));
            upm.showPanelByKey("searching");
        });

        addTripBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("addTrip", new TripAddPanel());
            upm.showPanelByKey("addTrip");
        });
        
        addVehicleBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("addVehicle", new VehicleAddPanel());
            upm.showPanelByKey("addVehicle");
        });

        addUserBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("addUser", new UserAddPanel());
            upm.showPanelByKey("addUser");
        });

        editUserBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("selectUserForEditPanel", new SelectUserForEditPanel());
            upm.showPanelByKey("selectUserForEditPanel");
        });
        
        accountBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("account", new AccountPanel(admin));
            upm.showPanelByKey("account");
        });

        //Add buttons to right panel

        rightPanel.add(editTripBtn);
        rightPanel.add(addTripBtn);
        rightPanel.add(addVehicleBtn);
        rightPanel.add(addUserBtn);
        rightPanel.add(editUserBtn);
        rightPanel.add(accountBtn);

        // Add Panels
        add(leftPanel);
        add(rightPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
}
