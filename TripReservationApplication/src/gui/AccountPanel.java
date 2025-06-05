package gui;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    private String firstName = "Mert";
    private String lastName = "Berrak";
    private String email = "mert@example.com";
    private String gender = "Male";
    private String password = "secret123"; // örnek şifre

    public AccountPanel() {
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(239, 228, 210));
        add(contentPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(350, 400));
        infoPanel.setBackground(new Color(239, 228, 210));

        // 🔸 İsim + Buton aynı satırda
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("Hello " + firstName + " " + lastName + "!");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        JButton updateNameButton = new JButton("Update Name");
        styleButton(updateNameButton);
        updateNameButton.setPreferredSize(new Dimension(150, 25));
        namePanel.add(nameLabel);
        namePanel.add(updateNameButton);
        infoPanel.add(namePanel);
        infoPanel.add(Box.createVerticalStrut(20));

        // 🔸 Email
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email: " + email);
        emailPanel.add(emailLabel);
        infoPanel.add(emailPanel);

        infoPanel.add(Box.createVerticalStrut(10));

        // 🔸 Şifre + Buton aynı satırda
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password: *********                                                           ");
        JButton updatePasswordButton = new JButton("Update Password");
        styleButton(updatePasswordButton);
        updatePasswordButton.setPreferredSize(new Dimension(180, 25));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(updatePasswordButton);
        infoPanel.add(passwordPanel);
        infoPanel.add(Box.createVerticalStrut(10));

        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.setBackground(Color.WHITE);
        JLabel phoneNumberLabel = new JLabel("Phone Number: 208793892                                                           ");
        JButton updateNumberButton = new JButton("Update Number");
        styleButton(updateNumberButton);
        updatePasswordButton.setPreferredSize(new Dimension(180, 25));
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(updateNumberButton);
        infoPanel.add(phoneNumberPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        // 🔸 Cinsiyet + ComboBox aynı satırda
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        JLabel genderLabel = new JLabel("Gender: ");
        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderCombo = new JComboBox<>(genders);
        genderCombo.setSelectedItem(gender);
        genderCombo.addActionListener(e -> {
            gender = (String) genderCombo.getSelectedItem();
            // Gerekirse başka bir yerde kullanabilirsin
        });
        genderPanel.add(genderLabel);
        genderPanel.add(genderCombo);
        infoPanel.add(genderPanel);
        infoPanel.add(Box.createVerticalStrut(20));

        // 🔸 Hakkımızda
        JButton aboutButton = new JButton("About Us");
        styleButton(aboutButton);
        aboutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "aabbcc", "About Us", JOptionPane.INFORMATION_MESSAGE);
        });
        infoPanel.add(aboutButton);
        infoPanel.add(Box.createVerticalStrut(20));

        // 🔸 Kaydet
        JButton saveButton = new JButton("Save Changes");
        styleButton(saveButton);
        infoPanel.add(saveButton);

        contentPanel.add(infoPanel);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(true);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(200, 30));
    }
}
