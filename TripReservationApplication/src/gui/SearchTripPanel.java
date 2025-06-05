package gui;

import javax.swing.*;
import java.awt.*;

public class SearchTripPanel extends JPanel {

    public SearchTripPanel() {
        setLayout(new GridBagLayout()); // Panel ortalanıyor

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(500, 300)); // TabbedPane büyütüldü

        tabbedPane.addTab("Bus 🚌", createSearchForm("Bus",tabbedPane ));
        tabbedPane.addTab("Flight", createSearchForm("Flight", tabbedPane));

        add(tabbedPane); // Ortalanmış şekilde eklendi
    }
     
    private JPanel createSearchForm(String vehicle, JTabbedPane tabbedPane) {
        JPanel outerPanel = new JPanel(new BorderLayout());
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 220)); // Küçük ve kompakt yapı

        if("Bus".equals(vehicle)){
        panel.setBackground(new Color(239, 228, 210));
        outerPanel.setBackground(new Color(239, 228, 210));
        }
        else {
        panel.setBackground(Color.WHITE);
        outerPanel.setBackground(Color.WHITE);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10); // Bileşenin etrafındaki boşluk (üst, sol, alt, sağ)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Yatay olarak genişlesin ama dikeyde sabit kalsı
      
        // Kalkış yeri
        JLabel fromLabel = new JLabel("From:");
        JComboBox<String> fromCombo = new JComboBox<>(new String[]{"İzmir", "Ankara", "İstanbul"});

        // Varış yeri
        JLabel toLabel = new JLabel("To:");
        JComboBox<String> toCombo = new JComboBox<>(new String[]{"Antalya", "Bursa", "Adana"});

        // Tarih
        JLabel dateLabel = new JLabel("Date:");

        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> dayCombo = new JComboBox<>();

        // Yıl aralığı (2024-2030)
        for (int i = 2024; i <= 2030; i++) {
            yearCombo.addItem(String.valueOf(i));
        }

        // Aylar 01–12
        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(String.format("%02d", i));
        }

        // Günler 01–31 
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(String.format("%02d", i));
        }

        // Tarih format etiketi
        JLabel dateFormatLabel = new JLabel("Format: YYYY-MM-DD");
        dateFormatLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        dateFormatLabel.setForeground(Color.GRAY);



        // Ara butonu
        JButton searchButton = new JButton("Search");
        styleButton(searchButton);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(fromLabel, gbc);
        gbc.gridx = 1;
        panel.add(fromCombo, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(toLabel, gbc);
        gbc.gridx = 1;
        panel.add(toCombo, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(yearCombo, gbc);
        gbc.gridx = 2;
        panel.add(monthCombo, gbc);
        gbc.gridx = 3;
        panel.add(dayCombo, gbc);

        row++;
        gbc.gridx = 1; gbc.gridy = row; gbc.gridwidth = 3;
        panel.add(dateFormatLabel, gbc);


        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3;
        gbc.insets = new Insets(15, 10, 10, 10);
        panel.add(searchButton, gbc);

        outerPanel.add(panel, BorderLayout.CENTER);
        return outerPanel;
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
