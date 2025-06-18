package gui;
        
import gui.passengerPanels.PaymentPanel;
import dto.TripDTO;
import gui.components.BackButton;
import static gui.components.StyleButtons.createStyledBlueButton;
import static gui.components.StyleButtons.createStyledBrownButton;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import reservation.model.Reservation;
import reservation.service.ReservationService;
import seat.Seat;
import trip.model.BusTrip;
import trip.model.Trip;
import trip.service.TripService;
import tripreservationapplication.MainFrame;
import user.model.User;
import user.model.Admin;
import user.model.Passenger;

//Panel where Passenger can choose a seat and Admin can edit trips and reservations.
public class SeatSelect_TripEditPanel extends JPanel {

    private List<Seat> seatList;
    private User user;
    private List<Seat> selectedSeats = new ArrayList<>();
    TripService tripService = new TripService();
    ReservationService reservationService = new ReservationService();

    public SeatSelect_TripEditPanel(Trip trip, User user) {
        this.user = user;
        this.seatList = trip.getVehicle().getSeatList();  //The seats for the selected trip are listed.
                
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));      

        // Seat images are uploaded
        ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeatForPlane.jpg"));
        ImageIcon bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeatForPlane.jpg"));
        
        //Since the pixels of Bus and Plane seats are different, I am adding seat images in different sizes.
        if(trip instanceof BusTrip){ 
            emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeat.jpg"));
            bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeat.jpg"));
        }

        //The panel where the seats will be displayed is created (Seat panel)
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        wrapperPanel.setOpaque(false);

        JPanel seatPanel; // Seats will be added according to this panel. Therefore, they should be separate for bus and flight.

        //The seating arrangement of BusTrip and FlightTrip is different.
        if (trip instanceof BusTrip) {
            seatPanel = new JPanel(new GridLayout(4, 15, 10, 10)); //There are 4 rows and 15 columns.
            seatPanel.setOpaque(false);

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 15; col++) {
                    if (row == 1) { 
                        //No button is added to this line as it will represent the corridor.
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } 
                    else {
                        int index;
                        //Index is changed according to row order.
                        index = switch (row) {
                            case 0 -> col * 3;
                            case 2 -> col * 3 + 1;
                            default -> col * 3 + 2; //row == 3
                        };
                        
                        //A button for the seat with index number is created.
                        if (index < seatList.size()) {
                            Seat seat = seatList.get(index);
                            JButton seatButton = createSeatButton(seat, emptyIcon, bookedIcon, trip);

                            // Small panel for writing numbers underneath
                            JPanel seatWithLabel = new JPanel();
                            seatWithLabel.setLayout(new BoxLayout(seatWithLabel, BoxLayout.Y_AXIS));
                            seatWithLabel.setOpaque(false);
                            seatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel label = new JLabel(String.valueOf(seat.getSeatNumber()));
                            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                            label.setAlignmentX(Component.CENTER_ALIGNMENT);

                            seatWithLabel.add(seatButton);
                            seatWithLabel.add(label);

                            seatPanel.add(seatWithLabel);
                        } 
                        else {
                            JPanel empty = new JPanel();
                            empty.setOpaque(false);
                            seatPanel.add(empty);
                        }
                    }
                }
            }
        }
        //if (Trip instanceof FlightTrip)
        else {
            seatPanel = new JPanel(new GridLayout(7, 25, 10, 10)); //There will be 7 rows and 25 columns.
            seatPanel.setOpaque(false);

            //Row letters (4th row will be empty because it will represent the corridor)
            char[] seatRowChars = {'A', 'B', 'C', '-', 'D', 'E', 'F'};

            for (char rowChar : seatRowChars) {
                for (int col = 1; col <= 25; col++) {
                    //No seats will be added to the row representing the corridor.
                    if (rowChar == '-') { 
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } 
                    else {
                        String seatNumber = col + "" + rowChar;

                        //Find the right seat from seatList
                        Seat seat = null;
                        for (Seat s : seatList) {
                            if (s.getSeatNumber().equals(seatNumber)) {
                                seat = s;
                                break;
                            }
                        }
                        
                        //A button representing the seat and a label with the name of the seat are created.
                        if (seat != null) {
                            JButton seatButton = createSeatButton(seat, emptyIcon, bookedIcon, trip);

                            //Seat button + seat name label
                            JPanel seatWithLabel = new JPanel();
                            seatWithLabel.setLayout(new BoxLayout(seatWithLabel, BoxLayout.Y_AXIS));
                            seatWithLabel.setOpaque(false);
                            seatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                            //Label showing seat name
                            JLabel seatName = new JLabel(seat.getSeatNumber());
                            seatName.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                            seatName.setAlignmentX(Component.CENTER_ALIGNMENT);

                            seatWithLabel.add(seatButton);
                            seatWithLabel.add(seatName);

                            seatPanel.add(seatWithLabel);
                        } 
                        else {
                            JPanel empty = new JPanel();
                            empty.setOpaque(false);
                            seatPanel.add(empty);
                        }
                    }
                }
            }
        }
        
        wrapperPanel.add(seatPanel);
        
        // Top panel to hold BackButton and seat panel together
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(new BackButton("trips"), BorderLayout.NORTH); //The BackButton is called.
        topWrapper.add(wrapperPanel, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH); //Now only this single panel is added to NORTH


        //Panel of buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        //Passenger specific buttons
        if (user instanceof Passenger){
            //Information is displayed according to trip type.
            if (trip instanceof BusTrip) {
                JLabel extraChargeLabel = new JLabel("Single seats +50 TL");
                extraChargeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                extraChargeLabel.setForeground(new Color(150, 0, 0));
                buttonPanel.add(extraChargeLabel);
            }
            else {
                JLabel extraChargeLabel = new JLabel("Business Class (twice the fare): 1,2,3,4 and 5th row seats");
                extraChargeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                extraChargeLabel.setForeground(new Color(150, 0, 0));
                buttonPanel.add(extraChargeLabel);
            }
            
            //Payment Button
            //Button (created with the static method in the StyleButtons class)
            JButton payButton = createStyledBlueButton("Make Payment");
            payButton.addActionListener(e -> {
                //If there is no seat selected, you will not be directed to the PaymentPanel.
                if (selectedSeats.isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Please select a seat.","No Seat Selected",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("paymentPanel", new PaymentPanel(selectedSeats, trip, (Passenger) user));
                upm.showPanelByKey("paymentPanel");
            });
            buttonPanel.add(payButton);
        }
        
        //Admin specific buttons
        if (user instanceof Admin) {
            //Buttons below created with the static method in the StyleButtons class
            
            //Button for the popup where reservation information can be viewed and reservations can be deleted.
            JButton viewReservationBtn = createStyledBlueButton("View Reservation Information");
            viewReservationBtn.addActionListener(e -> showReservationsPopup(trip));
            
            //Button for popup where you can edit the trip
            JButton editTripBtn = createStyledBlueButton("Edit Trip");
            editTripBtn.addActionListener(e -> showTripEditPopup(trip));
            
            //Button to delete the trip
            JButton deleteTripBtn = createStyledBlueButton("Delete Trip");
            deleteTripBtn.addActionListener(e -> {
                tripService.cancelTrip(trip);
                int result = JOptionPane.showOptionDialog(null,"Trip deleted successfully.","Trip Deleted",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"}, "Okey");
                if (result == 0) { // When the Okey button is clicked:
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.setMenuBarVisible(true); 
                    upm.showPanelByKey("searching"); // Return to SearchTripPanel
                }
            });
            
            buttonPanel.add(viewReservationBtn);
            buttonPanel.add(editTripBtn);
            buttonPanel.add(deleteTripBtn);
        }
        add(buttonPanel, BorderLayout.SOUTH);
    }
//-------------------------------------------End of constructor method-----------------------------

    //Popup for admin to edit current trip
    private void showTripEditPopup(Trip trip) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Trip", true);
        dialog.setSize(500, 550);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(239, 228, 210));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Departure Date - Year, Month, Day
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Departure Date:"), gbc);
        gbc.gridx = 1;
        LocalDateTime depDate = trip.getDepartureDate();
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField yearField = new JTextField(String.valueOf(depDate.getYear()), 4);
        JTextField monthField = new JTextField(String.valueOf(depDate.getMonthValue()), 2);
        JTextField dayField = new JTextField(String.valueOf(depDate.getDayOfMonth()), 2);
        datePanel.add(new JLabel("Year:")); datePanel.add(yearField);
        datePanel.add(new JLabel("Month:")); datePanel.add(monthField);
        datePanel.add(new JLabel("Day:")); datePanel.add(dayField);
        dialog.add(datePanel, gbc);

        // Departure Time - Hour, Minute
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Departure Time:"), gbc);
        gbc.gridx = 1;
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField hourField = new JTextField(String.valueOf(depDate.getHour()), 2);
        JTextField minuteField = new JTextField(String.valueOf(depDate.getMinute()), 2);
        timePanel.add(new JLabel("Hour:")); timePanel.add(hourField);
        timePanel.add(new JLabel("Minute:")); timePanel.add(minuteField);
        dialog.add(timePanel, gbc);

        // Fare
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Fare:"), gbc);
        gbc.gridx = 1;
        JTextField fareField = new JTextField(String.valueOf(trip.getFare()), 10);
        dialog.add(fareField, gbc);

        //Travel Time
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Travel Time:"), gbc);
        gbc.gridx = 1;
        JPanel tripTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField tripHourField = new JTextField(String.valueOf(trip.getTripTime().getHour()), 2);
        JTextField tripMinuteField = new JTextField(String.valueOf(trip.getTripTime().getMinute()), 2);
        tripTimePanel.add(new JLabel("Hour:")); tripTimePanel.add(tripHourField);
        tripTimePanel.add(new JLabel("Minute:")); tripTimePanel.add(tripMinuteField);
        dialog.add(tripTimePanel, gbc);

        //Save button
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                //The information in the fields is taken and converted to LocalDateTime type.
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                LocalDateTime newDeparture = LocalDateTime.of(year, month, day, hour, minute);

                int tripHour = Integer.parseInt(tripHourField.getText());
                int tripMinute = Integer.parseInt(tripMinuteField.getText());

                //Trip information is updated.
                TripDTO tripDTO = new TripDTO();
                //Since the Arrival and Departure stations have not changed, they are pulled from the old information.
                tripDTO.setDepartureStation(trip.getDepartureStation());
                tripDTO.setArrivalStation(trip.getArrivalStation());
                tripDTO.setDepartureDate(newDeparture);
                tripDTO.setFare(Double.parseDouble(fareField.getText()));
                tripDTO.setTripTime(LocalTime.of(tripHour, tripMinute));
                tripService.updateTrip(trip, tripDTO);
                
                dialog.dispose();
                
                int result1 = JOptionPane.showOptionDialog(null,"Trip edited successfully.","Trip Edited",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"}, "Okey");
                if (result1 == 0) { // When the Okey button is clicked:
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.setMenuBarVisible(true); 
                    upm.showPanelByKey("searching"); // Return to SearchTripPanel
                }                
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Entry: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.add(saveButton, gbc);
        dialog.setVisible(true);
    }

    //Popup for admin to view and cancel reservations
    private void showReservationsPopup(Trip trip) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Rezervations", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(239, 228, 210));

        List<Reservation> reservations = trip.getReservations(); //Reservations are kept on the list

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);

        //If there is a reservation, it is listed, if not, it is reported as not available.
        if (!reservations.isEmpty()) {
            for (Reservation res : reservations) {
                JPanel resPanel = new JPanel(new BorderLayout());
                resPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                resPanel.setOpaque(false);
               
                //Reservation information is displayed with the user name, surname and e-mail.
                String passengerInfo = res.getPassenger().getName() + " " + res.getPassenger().getSurname()+ " " + '(' + res.getPassenger().getEmail() + ')';
                JLabel infoLabel = new JLabel(passengerInfo);
                
                //Button for canceling the reservation (created with the static method in the StyleButtons class)
                JButton cancelBtn = createStyledBrownButton("Cancel");
                cancelBtn.addActionListener(e -> {
                    reservationService.deleteReservation(res);
                    reservations.remove(res);
                
                    int result = JOptionPane.showOptionDialog(dialog,"Reservation canceled successfully.","Canceled",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"},"Okey");
                    if (result == 0) { //When the Okey button is pressed
                        dialog.dispose(); // Close reservation popup
                    }
                });

                resPanel.add(infoLabel, BorderLayout.CENTER);
                resPanel.add(cancelBtn, BorderLayout.EAST);
                listPanel.add(resPanel);
            }
        } 
        else {
            JLabel noResLabel = new JLabel("There are no reservations for this trip.");
            noResLabel.setHorizontalAlignment(SwingConstants.CENTER);
            listPanel.add(noResLabel);
        }

        //A scroll is created in case the reservation list is long.
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(239, 228, 210));

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    //The method that creates the seats and allows them to be clicked
    private JButton createSeatButton(Seat seat, ImageIcon emptyIcon, ImageIcon bookedIcon, Trip trip) {
        JButton seatButton = new JButton();
        seatButton.setIcon(seat.getIsBooked() ? bookedIcon : emptyIcon);
        
        //The seat sizes shown for Bus and Plane are different.
        if(trip instanceof BusTrip){
            seatButton.setPreferredSize(new Dimension(40, 40));
        }
        else{
            seatButton.setPreferredSize(new Dimension(20, 20));
        }
        
        seatButton.setContentAreaFilled(false);
        seatButton.setBorderPainted(false);
        seatButton.setFocusPainted(false);
        
        //If the seat is already full, it should be unclickable for the passenger, and the Admin should not be able to click on it at all.
        if (user instanceof Passenger) {
            if (seat.getIsBooked()) {
                seatButton.setEnabled(false);
            } 
            else {                
                //Each time the button is clicked, the seat's selected status changes and the image appropriate to that current status is displayed.
                seatButton.addActionListener(e -> {
                    boolean newStatus = seatButton.getIcon().equals(emptyIcon);
                    seatButton.setIcon(newStatus ? bookedIcon : emptyIcon);
                    if (newStatus) selectedSeats.add(seat);
                    else selectedSeats.remove(seat);
                });
            }
        } 
        else { seatButton.setEnabled(false); }
       
        return seatButton;
    }
}