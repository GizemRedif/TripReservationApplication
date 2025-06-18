package gui.adminPanels;

import dto.VehicleDTO;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vehicle.model.Bus;
import vehicle.model.Plane;
import vehicle.model.Vehicle;
import vehicle.service.VehicleService;
import static gui.components.StyleButtons.createStyledBlueButton;


public class VehicleAddPanel extends JPanel {
    
    VehicleService vehicleService = new VehicleService();
    
    public VehicleAddPanel(){
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(new Color(239, 228, 210));
        
        // Vehicle Type
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Vehicle Type:"), gbc);
        String[] types = {"Bus", "Plane"};
        JComboBox<String> typeCombo = new JComboBox<>(types); 
        gbc.gridx = 1;
        add(typeCombo, gbc);

        // Vehicle Identifier
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Vehicle Identifier (Plate or tail number):"), gbc);
        JTextField vehicleIdentifier = new JTextField(15);
        gbc.gridx = 1;
        add(vehicleIdentifier, gbc);

        // Save Button (created with the static method in the StyleButtons class)
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = createStyledBlueButton("Save");
        add(saveButton, gbc);

        // Save Button Click
        saveButton.addActionListener(e -> {
            
            //Checking if Vehicle Identifier is empty
            if (vehicleIdentifier.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in the blanks.", "Missing Information",JOptionPane.WARNING_MESSAGE);
            } 
            else { //Vehicle is created
                Class< ? extends Vehicle> vehicleType;
                int capacity;
                
                if(((String)typeCombo.getSelectedItem()).equals("Bus")){
                    vehicleType = Bus.class;
                    capacity = 45;
                }
                else{
                    vehicleType = Plane.class;
                    capacity = 150;
                }
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setVehicleType(vehicleType);
                vehicleDTO.setVehicleIdentifier(vehicleIdentifier.getText());
                vehicleService.createVehicle(vehicleDTO);

                JOptionPane.showMessageDialog(this, "Vehicle created successfully!","Successful",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
