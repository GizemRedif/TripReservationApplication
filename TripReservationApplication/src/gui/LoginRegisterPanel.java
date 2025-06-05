package gui;

import javax.swing.*;
import java.awt.*;
import user.model.Passenger;

import user.model.User; //User sınıfına erişebilmek için


public class LoginRegisterPanel extends JPanel {

    public LoginRegisterPanel() {
        setLayout(new GridBagLayout()); // Ortalamak için kullanıyoruz
        this.setBackground(new Color(37, 77, 112));  // arkaplan rengi
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Başlık ile sekme arası boşluk
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        containerPanel.setBackground(Color.WHITE);

        // 🔹 Başlık paneli
        JLabel titleLabel = new JLabel("Uygulama Adı", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));  //Başlık yazı rengi
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // 🔹 Sekmeler altta olacak şekilde oluşturuluyor
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());

        // 🔹 Panellere ekleme sırası
        containerPanel.add(titleLabel, BorderLayout.NORTH);
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel); // Ortalanmış haliyle
    }
//
//    private void switchToUserMainPanel(User user) {
//    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//    topFrame.setContentPane(new UserMainPanel(user));
//    topFrame.revalidate();
//    topFrame.repaint();
//}

    
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
        
//        KONTROL İÇİN YAZILMILTI
//        loginButton.addActionListener(e -> {
//        String email = emailField.getText().trim();
//        String password = new String(passwordField.getPassword());
//        // Burada email ve password doğrulama işlemini yap
//        if (email.equals("admin@mail.com") && password.equals("admin123")) {
//            // admini al
//            switchToUserMainPanel(adminUser);
//        } else if (email.equals("passenger@mail.com") && password.equals("pass123")) {
//            //Passenger al
//            switchToUserMainPanel(passengerUser);
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
//        }
//    });

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
        
//        KULLANICI OLUŞTURMAK İÇİN YAZILMIŞTI
//        registerButton.addActionListener(e -> {
//            String firstName = firstNameField.getText().trim();
//            String lastName = lastNameField.getText().trim();
//            String email = emailField.getText().trim();
//            String password = new String(passwordField.getPassword());
//            String phoneNumber = phoneField.getText().trim();
//            String selectedGender = (String) genderCombo.getSelectedItem();
//            char gender = selectedGender.charAt(0);  // İlk harfi alır
// 
//            //paymentınfo değiştikten sonra düzelecek.
//            Passenger user = new Passenger(firstName, lastName, email, password, phoneNumber, gender);
//
//            System.out.println("User created: "); // Varsa toString() kullanılır
//        });

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
    button.setBackground(new Color(19, 29, 79)); // Yeni RGB rengi
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

}
