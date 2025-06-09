
package gui.subpanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TripAddPanel_Admin extends JPanel {
    
    public TripAddPanel_Admin(){
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(new Color(239, 228, 210));

        // Vehicle Type
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Vehicle Type:"), gbc);
        JTextField vehicleTypeField = new JTextField(15);
        gbc.gridx = 1;
        add(vehicleTypeField, gbc);

        // Departure Station
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Departure Station:"), gbc);
        JTextField departureField = new JTextField(15);
        gbc.gridx = 1;
        add(departureField, gbc);

        // Arrival Station
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Arrival Station:"), gbc);
        JTextField arrivalField = new JTextField(15);
        gbc.gridx = 1;
        add(arrivalField, gbc);

        // Departure Date
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Departure Date (Yıl - Ay - Gün - Saat - Dakika):"), gbc);
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField yearField = new JTextField(4);
        JTextField monthField = new JTextField(2);
        JTextField dayField = new JTextField(2);
        JTextField hourField = new JTextField(2);
        JTextField minuteField = new JTextField(2);
        datePanel.add(yearField);
        datePanel.add(monthField);
        datePanel.add(dayField);
        datePanel.add(hourField);
        datePanel.add(minuteField);
        gbc.gridx = 1;
        add(datePanel, gbc);

        // Trip Duration
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Trip Duration (Saat - Dakika):"), gbc);
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField durationHourField = new JTextField(2);
        JTextField durationMinuteField = new JTextField(2);
        durationPanel.add(durationHourField);
        durationPanel.add(durationMinuteField);
        gbc.gridx = 1;
        add(durationPanel, gbc);

        // Fare
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Fare:"), gbc);
        JTextField fareField = new JTextField(10);
        gbc.gridx = 1;
        add(fareField, gbc);

        // Save Button
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Kaydet");
        add(saveButton, gbc);

        // Kaydet butonuna tıklama kontrolü
        saveButton.addActionListener(e -> {
            if ( vehicleTypeField.getText().isEmpty() || departureField.getText().isEmpty() || arrivalField.getText().isEmpty() || yearField.getText().isEmpty() ||
                monthField.getText().isEmpty() || dayField.getText().isEmpty() || hourField.getText().isEmpty() || minuteField.getText().isEmpty() ||
                durationHourField.getText().isEmpty() ||  durationMinuteField.getText().isEmpty() || fareField.getText().isEmpty()
                ){
                JOptionPane.showMessageDialog(this, "Please fill in the blanks.", "Missing Information",JOptionPane.WARNING_MESSAGE);
            } 
            else {
                
                
                // VERİLERİ İSLEME KODLARI

                JOptionPane.showMessageDialog(this, "Trip created successfully!","Successful",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
        
        
}
      

