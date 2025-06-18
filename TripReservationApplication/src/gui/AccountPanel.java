package gui;

import static gui.components.StyleButtons.createStyledBlueButton;
import static gui.components.StyleButtons.createStyledBrownButton;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import user.model.User;

public class AccountPanel extends JPanel {

    private String firstName;
    private String lastName;
    private String email;
    private char gender;
    private String password;
    private String phoneNumber;

    //If the user updates their information, they will be used to display new information in the My Account section.
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel phoneNumberLabel;
    private JLabel genderLabel;
    
    public AccountPanel(User user) {
        
        //User information is obtained
        firstName = user.getName();
        lastName = user.getSurname();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        gender = user.getGender();
        password = user.getPassword();
        
        UserService userService = new UserService(); //Will be used to update user information

        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(239, 228, 210));
        add(contentPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(350, 400));
        infoPanel.setBackground(new Color(239, 228, 210));

        
        //Name Panel---------------------------------------------------------------------------------------
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(Color.WHITE);
        nameLabel = new JLabel("Hello " + firstName + " " + lastName + "!");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        namePanel.add(nameLabel);
        infoPanel.add(namePanel);
        infoPanel.add(Box.createVerticalStrut(20));
        
        
        //Email Panel---------------------------------------------------------------------------------------
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        emailPanel.setBackground(Color.WHITE);
        emailLabel = new JLabel("Email: " + email);
        emailPanel.add(emailLabel);
        infoPanel.add(emailPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Password panel---------------------------------------------------------------------------------------
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(Color.WHITE);
        passwordLabel = new JLabel("Password: *********");
        passwordPanel.add(passwordLabel);
        infoPanel.add(passwordPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Phone Number Panel---------------------------------------------------------------------------------------
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.setBackground(Color.WHITE);
        phoneNumberLabel = new JLabel("Phone Number: " + phoneNumber);
        phoneNumberPanel.add(phoneNumberLabel);
        infoPanel.add(phoneNumberPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Gender Panel---------------------------------------------------------------------------------------
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderLabel = new JLabel("Gender: " + gender);
        genderPanel.add(genderLabel);
        infoPanel.add(genderPanel);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //About US Panel---------------------------------------------------------------------------------------
        //Button (created with the static method in the StyleButtons class)
        JButton aboutButton = createStyledBlueButton("About Us");
        aboutButton.addActionListener(e -> {
            ImageIcon us = new ImageIcon(getClass().getResource("/gui/pictures/us.jpg"));
            Image scaledImage = us.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH); // The image is resized
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(this, "Our days passed here :)","About Us", JOptionPane.INFORMATION_MESSAGE, scaledIcon);
        });
        infoPanel.add(aboutButton);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //Change Information Panel---------------------------------------------------------------------------------------
        //Button (created with the static method in the StyleButtons class)
        JButton changeInfButton = createStyledBlueButton("Change Information");
        infoPanel.add(changeInfButton);
        //When the Change Inf button is clicked, a pop-up will appear and the user will be able to update what they want.
        changeInfButton.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
            //EditUser paneli acılacak.
            upm.addPanel("editUser", new EditUserPanel(user, "accountPanel", () -> {
                this.updateLabels(user); //Update labels for this panel 
                upm.showPanelByKey("account"); //Then come back
            }));
            upm.showPanelByKey("editUser");
        });

        
        // Logout Button Panel---------------------------------------------------------------------------------------
        //Button (created with the static method in the StyleButtons class)    
        JButton logoutButton = createStyledBlueButton("Logout");
        logoutButton.addActionListener(e -> {           
            int result = JOptionPane.showOptionDialog(null,"Do you want to log out?","Log Out",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Yes"}, "Yes");
            if (result == 0) { //When you click the Yes button, you will return to the LoginRegisterPanel.
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane(); //To hide the MenuBar
                upm.setMenuBarVisible(false); //Menu is hidden
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.setContentPane(new LoginRegisterPanel());  //When you log out, you will be returned to the LoginRegisterPanel.
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(logoutButton);

        // Delete Account Button Panel---------------------------------------------------------------------------------------
        //Button (created with the static method in the StyleButtons class)    
        JButton deleteAccountButton = createStyledBrownButton("Delete Account");
        
        deleteAccountButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(null,"Do you want to delete this account?","Account Delete",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Yes"}, "Yes");
            if (result == 0) { //When the Okey button is pressed, the user is deleted and the user returns to the LoginRegisterPanel.
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane(); //To hide the MenuBar
                upm.setMenuBarVisible(false); //Menu is hidden
                userService.deleteUser(user.getEmail());
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.setContentPane(new LoginRegisterPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(deleteAccountButton);

        
        contentPanel.add(infoPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
       
    //The information displayed in the Account section is updated.
    public void updateLabels(User updatedUser) {
        nameLabel.setText("Hello " + updatedUser.getName() + " " + updatedUser.getSurname() + "!");
        phoneNumberLabel.setText("Phone Number: " + updatedUser.getPhoneNumber());
        genderLabel.setText("Gender: " + updatedUser.getGender());
        passwordLabel.setText("Password: *********");
    }
}