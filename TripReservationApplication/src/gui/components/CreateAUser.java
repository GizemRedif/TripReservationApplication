package gui.components;

import dto.UserDTO;
import user.model.Passenger;
import javax.swing.*;
import java.awt.*;
import user.Service.UserService;
import user.model.Admin;
import static gui.components.StyleButtons.createStyledBlueButton;

//It is called in places where users are created. (Passenger and Admin)
//LoginRegisterPanel -> Register field (Passenger create)
//AdminPanel -> UserAddPanel -> Admin Add and Passenger Add fields

public class CreateAUser extends JPanel {

    private final UserService userService;
    private final JTabbedPane tabbedPane;
    
    public CreateAUser(UserService userService, JTabbedPane tabbedPane, boolean isTabbedChange, String userType) {
        this.userService = userService;
        this.tabbedPane = tabbedPane;

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(239, 228, 210));

        //Labels for entering user information:
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        //Button (created with the static method in the StyleButtons class)
        JButton registerButton = createStyledBlueButton("Register");
        registerButton.addActionListener(e -> {
            String selectedGender = (String) genderCombo.getSelectedItem();
            
            //When the Save button is clicked, a new user is created with UserDTO.
            UserDTO newUser = new UserDTO();
            newUser.setName(firstNameField.getText().trim());
            newUser.setSurname(lastNameField.getText().trim());
            newUser.setEmail(emailField.getText().trim());
            newUser.setPassword(new String(passwordField.getPassword()));
            newUser.setPhoneNumber(phoneField.getText().trim());
            newUser.setGender(selectedGender.charAt(0));
            if(userType.equalsIgnoreCase("admin")){newUser.setUserType(Admin.class);}
            else{newUser.setUserType(Passenger.class);}
            
            try {
                userService.createUser(newUser);

                JOptionPane.showMessageDialog(this, "User created successfully.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                if(isTabbedChange == true){ tabbedPane.setSelectedIndex(0);} //Return to login panel
            } 
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error occurred:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(genderLabel);
        formPanel.add(genderCombo);
        formPanel.add(new JLabel()); // space
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
    }
//-------------------------------------------End of constructor method-----------------------------
}
