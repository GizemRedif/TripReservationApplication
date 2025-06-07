package gui;

import javax.swing.*;
import java.awt.*;
import user.Service.UserService;
import user.model.User;

public class AccountPanel extends JPanel {

    private String firstName;
    private String lastName;
    private String email;
    private char gender;
    private String password;
    private String phoneNumber;

    //Kullanıcı bilgilerini guncellerse hesabım kısmında yeni bilgilerin gorunebilmesi icin kullanılacaklar
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel phoneNumberLabel;
    private JLabel genderLabel;
    
    public AccountPanel(User user) {
        
        //User'ın bilgileri alınır
        firstName = user.getName();
        lastName = user.getSurname();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        gender = user.getGender();
        password = user.getPassword();
        
        UserService userService = new UserService(); //Kullanıcı bilgilerini gunellemek icin kullanılacak

        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(239, 228, 210));
        add(contentPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(350, 400));
        infoPanel.setBackground(new Color(239, 228, 210));

        
        //Name Panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(Color.WHITE);
        nameLabel = new JLabel("Hello " + firstName + " " + lastName + "!");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        namePanel.add(nameLabel);
        infoPanel.add(namePanel);
        infoPanel.add(Box.createVerticalStrut(20));
        
        
        //Email Panel
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        emailPanel.setBackground(Color.WHITE);
        emailLabel = new JLabel("Email: " + email);
        emailPanel.add(emailLabel);
        infoPanel.add(emailPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Password panel
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(Color.WHITE);
        passwordLabel = new JLabel("Password: *********                                                           ");
        passwordPanel.add(passwordLabel);
        infoPanel.add(passwordPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Phone Number Panel
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.setBackground(Color.WHITE);
        phoneNumberLabel = new JLabel("Phone Number: " + phoneNumber + "                                                           ");
        phoneNumberPanel.add(phoneNumberLabel);
        infoPanel.add(phoneNumberPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Gender Panel
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderLabel = new JLabel("Gender: ");
        genderPanel.add(genderLabel);
        infoPanel.add(genderPanel);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //About US Panel
        JButton aboutButton = new JButton("About Us");
        styleButton(aboutButton);
        aboutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "aabbcc", "About Us", JOptionPane.INFORMATION_MESSAGE);
        });
        infoPanel.add(aboutButton);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //Change Information Panel
        JButton changeInfButton = new JButton("Change Information");
        styleButton(changeInfButton);
        infoPanel.add(changeInfButton);
        
        //Change Inf butonuna basılınca bir pop-up cıkacak ve kullanıcı istedigini guncelleyecek.
        changeInfButton.addActionListener(e -> {
            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Update Information", true);
            dialog.setSize(400, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new GridBagLayout());

            //Bileşenlerin (label, textfield, buton vs.) konumlandırma ve boyutlandırma kuralları tanımlanır.
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JTextField nameField = new JTextField(user.getName(), 20);
            JTextField surnameField = new JTextField(user.getSurname(), 20);
            JTextField emailField = new JTextField(user.getEmail(), 20);
            emailField.setEditable(false); // Kullanıcı e-mailini düzenleyemez
            emailField.setBackground(Color.LIGHT_GRAY); // Daha net görünmesi için
            JPasswordField passwordField = new JPasswordField(user.getPassword(), 20);
            JTextField phoneField = new JTextField(user.getPhoneNumber(), 20);

            JLabel changeGenderLabel = new JLabel("Gender:");
            String[] genders = {"Male", "Female", "Other"}; // M: Male, F: Female, O: Other
            JComboBox<String> genderCombo = new JComboBox<>(genders);
            genderCombo.setSelectedItem(String.valueOf(user.getGender()));

            JButton saveButton = new JButton("Save");
            styleButton(saveButton);
            JButton cancelButton = new JButton("Cancel");
            styleButton(cancelButton);

            int y = 0;

            dialog.add(new JLabel("First Name:"), gbcAt(gbc, 0, y));  //gbcAt ile her bileşen için gbc.gridx = ..., gbc.gridy = ... yazmaktan kurtulduk.
            dialog.add(nameField, gbcAt(gbc, 1, y++));
            dialog.add(new JLabel("Last Name:"), gbcAt(gbc, 0, y));
            dialog.add(surnameField, gbcAt(gbc, 1, y++));
            dialog.add(new JLabel("Email:"), gbcAt(gbc, 0, y));
            dialog.add(emailField, gbcAt(gbc, 1, y++));
            dialog.add(new JLabel("Password:"), gbcAt(gbc, 0, y));
            dialog.add(passwordField, gbcAt(gbc, 1, y++));
            dialog.add(new JLabel("Phone Number:"), gbcAt(gbc, 0, y));
            dialog.add(phoneField, gbcAt(gbc, 1, y++));
            dialog.add(changeGenderLabel, gbcAt(gbc, 0, y));
            dialog.add(genderCombo, gbcAt(gbc, 1, y++));

            gbc.gridwidth = 1;
            dialog.add(saveButton, gbcAt(gbc, 0, y));
            dialog.add(cancelButton, gbcAt(gbc, 1, y));

            
            //Kaydet butonuna tıklayınca kontroller yapılır ve sorun yoksa bilgiler guncellenir
            saveButton.addActionListener(ev -> {

                // Eğer kullanıcı alanları değiştirmişse ve boş değilse, onları set et
                if (!nameField.getText().trim().equals(user.getName())) {
                    user.setName(nameField.getText().trim());
                }

                if (!surnameField.getText().trim().equals(user.getSurname())) {
                    user.setSurname(surnameField.getText().trim());
                }

                String newPassword = new String(passwordField.getPassword()).trim();
                if (!newPassword.isEmpty() && !newPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                }

                if (!phoneField.getText().trim().equals(user.getPhoneNumber())) {
                    user.setPhoneNumber(phoneField.getText().trim());
                }

                char selectedGender = genderCombo.getSelectedItem().toString().charAt(0);
                if (selectedGender != user.getGender()) {
                    user.setGender(selectedGender); // doğrudan güncelleniyor çünkü bu alan kontrol edilmiyor
                }

                try {
                    boolean success = userService.updateUser(user);
                    if (success) {
                        // GUI'deki user nesnesi güncelleniyor
                        nameLabel.setText("Hello " + user.getName() + " " + user.getSurname() + "!");
                        phoneNumberLabel.setText("Phone Number: " + user.getPhoneNumber());
                        genderLabel.setText("Gender: " + user.getGender());
                        // şifreyi göstermek istemiyorsan yıldızlı kalabilir
                        passwordLabel.setText("Password: *********");

                        JOptionPane.showMessageDialog(dialog, "Information updated successfully.");
                        dialog.dispose();
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });


            cancelButton.addActionListener(ev -> dialog.dispose());

            dialog.setVisible(true);
        });

        contentPanel.add(infoPanel);
    }

    
    private void styleButton(JButton button) {
        button.setFocusPainted(true);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(200, 30));
    }
    
    //Bu metot, GridBagLayout içinde bir bileşeni belirli bir hücreye (satır, sütun) yerleştirmek için kullanılan GridBagConstraints nesnesini kopyalar ve konumunu (gridx, gridy) belirler.
    private GridBagConstraints gbcAt(GridBagConstraints base, int x, int y) {
        GridBagConstraints copy = (GridBagConstraints) base.clone();  //mevcut constraints ayarlarının bir kopyasını çıkarır. Böylece aynı ayarları tekrar yazmadan kullanırsın.
        copy.gridx = x;
        copy.gridy = y;
        return copy;
    }

}
