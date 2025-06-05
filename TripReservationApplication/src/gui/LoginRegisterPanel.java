package gui;

import javax.swing.*;
import java.awt.*;

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
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Select", "Male", "Female", "Other"});

        JButton registerButton = new JButton("Register");
        styleButton(registerButton);

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
