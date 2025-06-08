package gui;

import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
//import user.model.Admin;
import user.model.Passenger;
import user.model.User; 


public class LoginRegisterPanel extends JPanel {

    UserService userService = new UserService(); //Bu sınıftaki createUser ve login metotlarını kullanabilmek icin olusturuldu
    private final JTabbedPane tabbedPane; // Ekledik

    public LoginRegisterPanel() {
        setLayout(new GridBagLayout()); 
        this.setBackground(new Color(37, 77, 112)); 
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Başlık ile sekme arası boşluk
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        containerPanel.setBackground(Color.WHITE);

        // 🔹 Başlık paneli
        JLabel titleLabel = new JLabel("Uygulama Adı", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));  
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // 🔹 Sekmeler altta olacak şekilde oluşturuluyor
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());

        // 🔹 Panellere ekleme sırası
        containerPanel.add(titleLabel, BorderLayout.NORTH);
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
    
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        
        loginButton.addActionListener(e -> {
            User user = userService.login(emailField.getText().trim(), new String(passwordField.getPassword()));           
            if(user == null){
                JOptionPane.showMessageDialog(null, "Email or password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {  
                //Giris yapan User'ın, Passanger mi Admin mi oldugu kontrolu yapılır ve ona gore panel acılır. 
                MainFrame.getInstance().showUserPanelManager(user);
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // boşluk
        panel.add(loginButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

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
        
//        KULLANICI OLUŞTURMAK İÇİN BUTONA TIKLANIR
        registerButton.addActionListener(e -> {
            String selectedGender = (String) genderCombo.getSelectedItem(); //Builderda setGender char istiyor. Gender burada önce stringe çevrilir, aşağıda ilk harfi alınır.
            
            //User'da Builder sınıfı kullanılarak yeni kullanıcı oluşturuluyor. 
            User newUser = new Passenger.PassengerBuilder() 
                    .setName(firstNameField.getText().trim())
                    .setSurname(lastNameField.getText().trim())
                    .seteMail(emailField.getText().trim())
                    .setPassword(new String(passwordField.getPassword()))
                    .setPhoneNumber(phoneField.getText().trim())
                    .setGender(selectedGender.charAt(0)).build();
            
            
            //KULLANICI HATALI INPUT GİRDİ Mİ KONTROL EDİLİR, GİRMEDİYSE KULLANICI OLUŞTURULUR.
            try {
                userService.createUser(newUser);
                System.out.println("User created: ");
                JOptionPane.showMessageDialog(null, "User created successfully. Please log in.!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                tabbedPane.setSelectedIndex(0); //Giris basarılı olunca Login ekranına donulsun
            } 
            catch (IllegalArgumentException a) {
                JOptionPane.showMessageDialog(null, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
            catch (Exception a) {
                JOptionPane.showMessageDialog(null, "An unexpected error occurred:\n" + a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(genderLabel);
        panel.add(genderCombo);
        panel.add(new JLabel()); // boşluk
        panel.add(registerButton);

        return panel;
    }

    private void styleButton(JButton button) {
    button.setFocusPainted(false);
    button.setBackground(new Color(19, 29, 79));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
