package gui.adminPanels;

import gui.components.CreateAUser;
import javax.swing.*;
import java.awt.*;
import user.Service.UserService;

//Admin, user eklemek icin kullanacak. (Passenger ve Admin ekleyebilir)
public class UserAddPanel extends JPanel {

    UserService userService = new UserService(); 
    private final JTabbedPane tabbedPane; 

    public UserAddPanel() {
        setLayout(new GridBagLayout()); 
        this.setBackground(new Color(37, 77, 112)); 
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Başlık ile sekme arası boşluk
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        containerPanel.setBackground(Color.WHITE);

        // Sekmeler altta olacak şekilde oluşturuluyor
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Admin Add", createCreateUserPanel("Admin"));
        tabbedPane.addTab("Passenger Add", createCreateUserPanel("Passenger"));

        // Panellere ekleme sırası
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
 
    
    private JPanel createCreateUserPanel(String userType) {             
        return new CreateAUser(userService, tabbedPane,true,userType);
    }
}
