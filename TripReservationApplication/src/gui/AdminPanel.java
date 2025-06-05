package gui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    public AdminPanel() {
        setLayout(new GridBagLayout()); // Ortalamak için
        this.setBackground(new Color(37, 77, 112));  // Arkaplan

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20));
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        containerPanel.setBackground(Color.WHITE);

        // 🔹 Sekmeler
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Edit/Delete Trip", createTripEditDeletePanel());
        tabbedPane.addTab("Tripp Add", createTripAddPanel());
        tabbedPane.addTab("Admin Add", createAdminAddPanel());
        tabbedPane.addTab("User Delete", createUserDeletePanel());

        containerPanel.add(tabbedPane, BorderLayout.CENTER);
        add(containerPanel);
    }

    
    private JPanel createTripEditDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Seyahat düzenleme/silme alanı", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTripAddPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Seyahat ekleme alanı", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createAdminAddPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Ad:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Soyad:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Email:"));
        panel.add(new JTextField());

        JButton addButton = new JButton("Admin Ekle");
        styleButton(addButton);
        panel.add(new JLabel()); // boşluk
        panel.add(addButton);

        return panel;
    }

    private JPanel createUserDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Kullanıcı silme alanı", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
