package gui.adminPanels;

import dto.TripDTO;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
import trip.service.TripService;
import vehicle.model.Vehicle;
import vehicle.service.VehicleService;
import static gui.components.StyleButtons.createStyledBlueButton;

public class TripAddPanel extends JPanel {
    
    VehicleService vehicleService = new VehicleService();
    TripService tripService = new TripService();
    private JComboBox<String> vehicleIdentifierCombo; //TripType'a gore Vehicle ıdentifierlarını listelemek icin kullanılacak
    
    public TripAddPanel(){
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(new Color(239, 228, 210));

        // Trip Type
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Trip Type:"), gbc);
        String[] types = {"Bus", "Plane"};
        JComboBox<String> typeCombo = new JComboBox<>(types);         
        gbc.gridx = 1;
        add(typeCombo, gbc);
        
        // Vehicle Identifier
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Vehicle Identifier:"), gbc);
        vehicleIdentifierCombo = new JComboBox<>();
        updateVehicleIdentifierCombo((String) typeCombo.getSelectedItem()); //updateVehicleIdentifierCombo metodu ile başlangıçta default listeyi yükler (Bus)

        // Trip Type seçimi değiştiğinde vehicle identifier listesini guncelle
        typeCombo.addActionListener(e -> {
            String selectedType = (String)typeCombo.getSelectedItem();
            updateVehicleIdentifierCombo(selectedType);
        });
        gbc.gridx = 1;
        add(vehicleIdentifierCombo, gbc);

        // Departure Station
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Departure Station:"), gbc);
        JTextField departureField = new JTextField(15);
        gbc.gridx = 1;
        add(departureField, gbc);

        // Arrival Station
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Arrival Station:"), gbc);
        JTextField arrivalField = new JTextField(15);
        gbc.gridx = 1;
        add(arrivalField, gbc);

        // Departure Date
        gbc.gridx = 0; gbc.gridy = 4;
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
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Trip Duration (Saat - Dakika):"), gbc);
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField durationHourField = new JTextField(2);
        JTextField durationMinuteField = new JTextField(2);
        durationPanel.add(durationHourField);
        durationPanel.add(durationMinuteField);
        gbc.gridx = 1;
        add(durationPanel, gbc);

        // Fare
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Fare:"), gbc);
        JTextField fareField = new JTextField(10);
        gbc.gridx = 1;
        add(fareField, gbc);

        // Save Button
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        //Button (created with the static method in the StyleButtons class)
        JButton saveButton = createStyledBlueButton("Save");
        add(saveButton, gbc);
        
        saveButton.addActionListener(e -> {
            if (departureField.getText().isEmpty() || arrivalField.getText().isEmpty() || yearField.getText().isEmpty() || monthField.getText().isEmpty() || durationMinuteField.getText().isEmpty() ||
                dayField.getText().isEmpty() || hourField.getText().isEmpty() || minuteField.getText().isEmpty() || durationHourField.getText().isEmpty() || fareField.getText().isEmpty()
                ){
                JOptionPane.showMessageDialog(this, "Please fill in the blanks.", "Missing Information",JOptionPane.WARNING_MESSAGE);
            } 
            else {
                //TextLabellerde alınan gun ve saat bilgileri, uygun ture donusturulmek icin kaydediliyor
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                
                int tripHour = Integer.parseInt(durationHourField.getText());
                int tripMinute = Integer.parseInt(durationMinuteField.getText());
                
                Class< ? extends Trip > TripType;
                if(((String)typeCombo.getSelectedItem()).equals("Bus")){TripType = BusTrip.class;}
                else{TripType = FlightTrip.class;}
                
                TripDTO tripDTO = new TripDTO();
                tripDTO.setTripType(TripType);
                tripDTO.setVehicle(vehicleService.getVehicleByIdentifier((String)(vehicleIdentifierCombo.getSelectedItem())));
                tripDTO.setDepartureStation(departureField.getText());
                tripDTO.setArrivalStation(arrivalField.getText());
                tripDTO.setDepartureDate(LocalDateTime.of(year, month, day, hour, minute));
                tripDTO.setTripTime(LocalTime.of(tripHour, tripMinute));
                tripDTO.setFare(Double.parseDouble(fareField.getText()));
                tripService.createTrip(tripDTO);
                        
                JOptionPane.showMessageDialog(this, "Trip created successfully!","Successful",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    //-------------------------------------------End of constructor method-----------------------------

    //Vehicle türüne göre listelenen identifierlar guncellenir.
    private void updateVehicleIdentifierCombo(String selectedType) {
        vehicleIdentifierCombo.removeAllItems(); // Eski item'ları temizle

        List<Vehicle> vehicleList;
        if (selectedType.equals("Bus")) {
            vehicleList = vehicleService.getAllBuses();
        } 
        else {
            vehicleList = vehicleService.getAllPlanes();
        }

        for (Vehicle v : vehicleList) {
            vehicleIdentifierCombo.addItem(v.getIdentifier());
        }
    }
}
      

