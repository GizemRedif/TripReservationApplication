package gui.subpanels;

import user.model.User;

import javax.swing.*;
import java.awt.*;
import user.Service.UserService;
import dto.UserDTO;
public class EditUserPanel extends JPanel {
UserService userService;
    //Runnable: içinde yapılacak bir şey tanımlanır, sonra bu iş çağrıldığında çalıştırılır.
    public EditUserPanel(User user, Runnable onUpdateSuccess) {
        userService = new UserService();
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(user.getName(), 20);
        JTextField surnameField = new JTextField(user.getSurname(), 20);
        JTextField emailField = new JTextField(user.getEmail(), 20);
        emailField.setEditable(false);
        emailField.setBackground(Color.LIGHT_GRAY);
        JPasswordField passwordField = new JPasswordField(user.getPassword(), 20);
        JTextField phoneField = new JTextField(user.getPhoneNumber(), 20);

        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setSelectedItem(getGenderString(user.getGender()));

        JButton saveButton = new JButton("Save");
        styleButton(saveButton);

        int y = 0;
        add(new JLabel("First Name:"), gbcAt(gbc, 0, y));
        add(nameField, gbcAt(gbc, 1, y++));
        add(new JLabel("Last Name:"), gbcAt(gbc, 0, y));
        add(surnameField, gbcAt(gbc, 1, y++));
        add(new JLabel("Email:"), gbcAt(gbc, 0, y));
        add(emailField, gbcAt(gbc, 1, y++));
        add(new JLabel("Password:"), gbcAt(gbc, 0, y));
        add(passwordField, gbcAt(gbc, 1, y++));
        add(new JLabel("Phone Number:"), gbcAt(gbc, 0, y));
        add(phoneField, gbcAt(gbc, 1, y++));
        add(new JLabel("Gender:"), gbcAt(gbc, 0, y));
        add(genderCombo, gbcAt(gbc, 1, y++));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = y;
        add(saveButton, gbc);

        saveButton.addActionListener(ev -> {
            UserDTO newUserDTO = new UserDTO();
            
            
                
                
            try {
                
                newUserDTO.setEmail(user.getEmail());
                newUserDTO.setName(nameField.getText().trim());
                newUserDTO.setSurname(surnameField.getText().trim());
                newUserDTO.setPassword(new String(passwordField.getPassword()).trim());
                newUserDTO.setPhoneNumber(phoneField.getText().trim());
                newUserDTO.setGender(genderCombo.getSelectedItem().toString().charAt(0));
                

                boolean updated = userService.updateUser(newUserDTO);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "User info updated.");
                    if (onUpdateSuccess != null) onUpdateSuccess.run(); // geri bildirim olarak kullanılabilir
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private String getGenderString(char genderChar) {
        return switch (Character.toUpperCase(genderChar)) {
            case 'M' -> "Male";
            case 'F' -> "Female";
            case 'O' -> "Other";
            default -> "Other";
        };
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private GridBagConstraints gbcAt(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }
}
