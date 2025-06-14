package gui;

import user.model.User;
import user.Service.UserService;
import dto.UserDTO;
import static gui.components.StyleButtons.createStyledBlueButton;
import static gui.components.StyleButtons.createStyledBrownButton;
import javax.swing.*;
import java.awt.*;

// AccountPanel -> Change Information butonu ile çağırılır.
// SelectUserForEditPanel -> admin kullanıcıyı seçip düzenlemek isterse çağırır.

public class EditUserPanel extends JPanel {
    private UserService userService;

    public EditUserPanel(User user, String callingPanel, Runnable onUpdateSuccess) {
        userService = new UserService();
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Kullanıcı bilgilerinin yer aldığı alanlar
        JTextField nameField = new JTextField(user.getName(), 20);
        JTextField surnameField = new JTextField(user.getSurname(), 20);
        JTextField emailField = new JTextField(user.getEmail(), 20);
        emailField.setEditable(false);
        emailField.setBackground(Color.LIGHT_GRAY);
        JPasswordField passwordField = new JPasswordField(user.getPassword(), 20);
        JTextField phoneField = new JTextField(user.getPhoneNumber(), 20);

        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setSelectedItem(getGenderString(user.getGender()));

        //Button (created with the static method in the StyleButtons class)
        JButton saveButton = createStyledBlueButton("Save");

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
                //Kullanıcı bilgileri güncellenir
                newUserDTO.setEmail(user.getEmail());
                newUserDTO.setName(nameField.getText().trim());
                newUserDTO.setSurname(surnameField.getText().trim());
                newUserDTO.setPassword(new String(passwordField.getPassword()).trim());
                newUserDTO.setPhoneNumber(phoneField.getText().trim());
                newUserDTO.setGender(genderCombo.getSelectedItem().toString().charAt(0));

                boolean updated = userService.updateUser(newUserDTO);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "User info updated.");
                    if (onUpdateSuccess != null) onUpdateSuccess.run();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Eğer çağıran panel SelectUserForEditPanel ise silme butonu eklenir ve admin passenger'i silebilir
        if ("selectUserForEditPanel".equals(callingPanel)) {
            //Button (created with the static method in the StyleButtons class)
            JButton deleteButton = createStyledBrownButton("Delete Account");
            gbc.gridy = ++y;  //Save butonu üzerine eklenmemesi için boşluk bırakılır
            add(deleteButton, gbc);

            deleteButton.addActionListener(ev -> {
                //Silmek istediğinden emin olunduktan sonra silme işlemi yapılır.
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = userService.deleteUser(user.getEmail());
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "User deleted successfully.");
                        if (onUpdateSuccess != null) onUpdateSuccess.run();
                    } 
                    else {
                        JOptionPane.showMessageDialog(this, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
//-------------------------------------------End of constructor method-----------------------------

    private String getGenderString(char genderChar) {
        return switch (Character.toUpperCase(genderChar)) {
            case 'M' -> "Male";
            case 'F' -> "Female";
            case 'O' -> "Other";
            default -> "Other";
        };
    }
    
    //Kod fazlalığını azaltmak için kullanılır.
    private GridBagConstraints gbcAt(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }
}
