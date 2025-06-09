package gui.subpanels;

import gui.components.CreateAUser;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import user.model.Passenger;
import user.model.User; 


public class UserAddForAdminPanel extends JPanel {

    UserService userService = new UserService(); //Bu sınıftaki createUser ve login metotlarını kullanabilmek icin olusturuldu
    private final JTabbedPane tabbedPane; // Ekledik

    public UserAddForAdminPanel() {
        setLayout(new GridBagLayout()); 
        this.setBackground(new Color(37, 77, 112)); 
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Başlık ile sekme arası boşluk
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        containerPanel.setBackground(Color.WHITE);


        // 🔹 Sekmeler altta olacak şekilde oluşturuluyor
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Admin Add", createCreateUserPanel("Admin"));
        tabbedPane.addTab("Passenger Add", createCreateUserPanel("Passenger"));

        // 🔹 Panellere ekleme sırası
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
 

    private JPanel createCreateUserPanel(String userType) {
        UserService userService = new UserService(); // kendi UserService implementasyonuna göre ayarla
               
        //USERTYPE EKLEDİM CONSTRUCTORA, USERTYPEA GORE NEW USER OLUSTURSUN. 
        return new CreateAUser(userService, tabbedPane,true);
    }

    private void styleButton(JButton button) {
    button.setFocusPainted(false);
    button.setBackground(new Color(19, 29, 79));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
