package gui;

import gui.subpanels.UserAddForAdminPanel;
import gui.subpanels.SelectUserForEditPanel_Admin;
import user.model.Admin;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;

public class AdminPanel extends JPanel {

    JLabel welcomeLabel;
    public AdminPanel(Admin admin) {
        setLayout(new GridLayout(1, 2));  // Sol ve sağ eşit genişlikte
        setBackground(new Color(239, 228, 210)); // Panel arkaplanı

        // 🔹 Sol Panel – "Welcome" yazısı
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(239, 228, 210));
        leftPanel.setLayout(new GridBagLayout());
        welcomeLabel = new JLabel("Welcome Admin!");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(welcomeLabel);

        // 🔹 Sağ Panel – Butonlar
        JPanel rightPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        rightPanel.setBackground(new Color(239, 228, 210));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Butonlar
        JButton editTripBtn = createStyledButton("Edit Trip/Reservation");
        JButton addTripBtn = createStyledButton("Trip Add");
        JButton addUserBtn = createStyledButton("User Add");
        JButton editUserBtn = createStyledButton("Edit User");
        JButton accountBtn = createStyledButton("My Account");

        // ActionListener'lar
        editTripBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("editTrip", new SearchTripPanel(admin));
            upm.showPanelByKey("editTrip");
        });

        addTripBtn.addActionListener(e -> {
//            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
//            upm.addPanel("addTrip", new TripAddPanel(admin));
//            upm.showPanelByKey("addTrip");
        });

        addUserBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("addUser", new UserAddForAdminPanel());
            upm.showPanelByKey("addUser");
        });

        editUserBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("selectUserForEditPanel", new SelectUserForEditPanel_Admin());
            upm.showPanelByKey("selectUserForEditPanel");
//        });
});
        
        accountBtn.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            upm.addPanel("account", new AccountPanel(admin));
            upm.showPanelByKey("account");
        });

        // Butonları sağ panele ekle
        rightPanel.add(editTripBtn);
        rightPanel.add(addTripBtn);
        rightPanel.add(addUserBtn);
        rightPanel.add(editUserBtn);
        rightPanel.add(accountBtn);

        // Panelleri ekle
        add(leftPanel);
        add(rightPanel);
    }
//-------------------------------------------End of constructor method-----------------------------

    
    
    // 🔧 Ortak buton tasarımı
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
    
}
