package gui.components;

import user.model.Passenger;
import user.model.User;

import javax.swing.*;
import java.awt.*;
import user.Service.UserService;

//User olusturulan yerlerde cagırılır. (Passenger ve Admin)
//LoginRegisterPanel -> Register alanı
//AdminPanel -> UserAddPanel -> Admin Add ve Passenger Add alanı

public class CreateAUser extends JPanel {

    private final UserService userService;
    private final JTabbedPane tabbedPane;
                                                                                             //, String userType
    public CreateAUser(UserService userService, JTabbedPane tabbedPane, boolean isTappedChange) {
        this.userService = userService;
        this.tabbedPane = tabbedPane;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

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

        JButton registerButton = new JButton("Register");
        styleButton(registerButton);

        registerButton.addActionListener(e -> {
            String selectedGender = (String) genderCombo.getSelectedItem();

            try {
                User newUser = new Passenger.PassengerBuilder()
                        .setName(firstNameField.getText().trim())
                        .setSurname(lastNameField.getText().trim())
                        .seteMail(emailField.getText().trim())
                        .setPassword(new String(passwordField.getPassword()))
                        .setPhoneNumber(phoneField.getText().trim())
                        .setGender(selectedGender.charAt(0))
                        .build();

                userService.createUser(newUser);

                JOptionPane.showMessageDialog(this, "User created successfully. Please log in!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
               if(isTappedChange == true){ tabbedPane.setSelectedIndex(0);} // Giriş paneline dön
                      
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error occurred:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bileşenleri ekle
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
        formPanel.add(new JLabel()); // boşluk
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
    }
//-------------------------------------------End of constructor method-----------------------------

    
    private void styleButton(JButton button) {
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}
