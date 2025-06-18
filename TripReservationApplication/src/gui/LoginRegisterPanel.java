package gui;

import gui.components.CreateAUser;
import static gui.components.StyleButtons.createStyledBlueButton;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import user.model.User; 


public class LoginRegisterPanel extends JPanel {

    UserService userService = new UserService(); //Created to be able to use the createUser and login methods in this class
    private final JTabbedPane tabbedPane; 

    public LoginRegisterPanel() {
        setLayout(new GridBagLayout()); 
        this.setBackground(new Color(37, 77, 112)); 
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Space between title and tab
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBackground(Color.WHITE);

        //Title panel
        JLabel titleLabel = new JLabel("THİS TİCKET", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(19, 29, 79));  //Text color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        //Tabs are created at the bottom
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());

        containerPanel.add(titleLabel, BorderLayout.NORTH);
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
    
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(239, 228, 210));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        //Button (created with the static method in the StyleButtons class)
        JButton loginButton = createStyledBlueButton("Login");
        
        loginButton.addActionListener(e -> {
            User user = userService.login(emailField.getText().trim(), new String(passwordField.getPassword()));           
            if(user == null){
                JOptionPane.showMessageDialog(null, "Email or password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {  
                //It is checked whether the logged in User is Passenger or Admin and the panel is opened accordingly.
                MainFrame.getInstance().showUserPanelManager(user);
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // space
        panel.add(loginButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        //The user creation panel is called and displayed in the tappedPane,
        return new CreateAUser(userService, tabbedPane,true, "Passenger");
    }
}
