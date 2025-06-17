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
    
    String[] cities = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
    "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
    "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
    "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

    
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
        JComboBox<String> fromCombo = new JComboBox<>(cities); 
        gbc.gridx = 1;
        add(fromCombo, gbc);

        // Arrival Station
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Arrival Station:"), gbc);
        JComboBox<String> toCombo = new JComboBox<>(cities);
        gbc.gridx = 1;
        add(toCombo, gbc);

        // Departure Date
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Departure Date (Year - Month - Day - Hour - Minute):"), gbc);
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
        add(new JLabel("Trip Duration (Hour - Minute):"), gbc);
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
            if (yearField.getText().isEmpty() || monthField.getText().isEmpty() || durationMinuteField.getText().isEmpty() ||
                dayField.getText().isEmpty() || hourField.getText().isEmpty() || minuteField.getText().isEmpty() || durationHourField.getText().isEmpty() || fareField.getText().isEmpty()
                ){
                JOptionPane.showMessageDialog(this, "Please fill in the blanks.", "Missing Information",JOptionPane.WARNING_MESSAGE);
            } 
            else {
                try{
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
                    tripDTO.setDepartureStation((String)fromCombo.getSelectedItem());
                    tripDTO.setArrivalStation((String)toCombo.getSelectedItem());
                    tripDTO.setDepartureDate(LocalDateTime.of(year, month, day, hour, minute));
                    tripDTO.setTripTime(LocalTime.of(tripHour, tripMinute));
                    tripDTO.setFare(Double.parseDouble(fareField.getText()));
                    tripService.createTrip(tripDTO);

                    JOptionPane.showMessageDialog(this, "Trip created successfully!","Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Unexpected error occurred:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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
      

