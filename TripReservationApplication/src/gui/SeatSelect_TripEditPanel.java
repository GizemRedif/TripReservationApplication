package gui;
        
import gui.passengerPanels.PaymentPanel;
import dto.TripDTO;
import gui.components.BackButton;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import reservation.model.Reservation;
import seat.Seat;
import trip.model.BusTrip;
import trip.model.Trip;
import trip.service.TripService;
import tripreservationapplication.MainFrame;
import user.model.User;
import user.model.Admin;
import user.model.Passenger;

public class SeatSelect_TripEditPanel extends JPanel {

    private List<Seat> seatList;
    private User user;
    private List<Seat> selectedSeats = new ArrayList<>();
    TripService tripService = new TripService();

    public SeatSelect_TripEditPanel(Trip trip, User user) {
        this.user = user;
        this.seatList = trip.getVehicle().getSeatList();  //Secilen trip'e ait koltuk bilgileri listelenir.
                
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));      

        // Koltuk resimleri yüklenir
        ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeatForPlane.jpg"));
        ImageIcon bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeatForPlane.jpg"));
        
        if(trip instanceof BusTrip){ //Bus ve Plane koltuklarının pixelleri farklı oldugu icin farklı boyutlarsad ekliyorum
            emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeat.jpg"));
            bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeat.jpg"));
        }

        //Koltukların gosterilecegi panel oluşturulur (Koltuk paneli)
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        wrapperPanel.setOpaque(false);

        JPanel seatPanel; // koltuklar bu panele gore eklenecek. O nedenle bus ve flight icin ayrı olmalı

        int seatIndex = 0;
        
        if (trip instanceof BusTrip) {
            int columns = seatList.size() / 3;
            seatPanel = new JPanel(new GridLayout(4, columns, 10, 10));
            seatPanel.setOpaque(false);

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < columns; col++) {
                    if (row == 1) {
                        // Boşluk satırı
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } else {
                        int index;
                        if (row == 0) {
                            index = col * 3;
                        } else if (row == 2) {
                            index = col * 3 + 1;
                        } else { // row == 3
                            index = col * 3 + 2;
                        }

                        if (index < seatList.size()) {
                            Seat seat = seatList.get(index);
                            JButton seatButton = createSeatButton(seat, emptyIcon, bookedIcon, trip);

                            // Altına numara yazmak için küçük panel
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
                        } else {
                            JPanel empty = new JPanel();
                            empty.setOpaque(false);
                            seatPanel.add(empty);
                        }
                    }
                }
            }
        }
        else { //FlightTrip ise
            seatPanel = new JPanel(new GridLayout(7, 25, 10, 10)); // 4 satır, 15 sütun
            seatPanel.setOpaque(false);
            for (int row = 0; row < 7; row++) {
                for (int col = 0; col < 25; col++) {
                    if (row == 3) { //Koltuk olmayan satır
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } 
                    else {
                        if (seatIndex < seatList.size()) {
                            Seat seat = seatList.get(seatIndex++);
                            JButton seatButton = createSeatButton(seat, emptyIcon, bookedIcon, trip);
                            seatPanel.add(seatButton);
                        }
                    }
                }
            }
        }
        wrapperPanel.add(seatPanel);
        
        // BackButton ve koltuk panelini birlikte tutacak üst panel
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(new BackButton("trips"), BorderLayout.NORTH); //BackButton cagırılıyor.
        topWrapper.add(wrapperPanel, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH); //Artık sadece bu tek panel NORTH'a ekleniyor


        // Altta kalan butonların paneli
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        // Passenger'e özel butonlar
        if (user instanceof Passenger){
            JButton payButton = new JButton("Make Payment");
            styleButton(payButton);
            payButton.addActionListener(e -> {
                if (selectedSeats.isEmpty()) { //Kullanıcı koltuk secmeden odeme yap butonuna tıklarsa hata alır.
                    JOptionPane.showMessageDialog(this,"Please select a seat.","No Seat Selected",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //Odeme yapılması icin PaymentPanel acılır.
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("paymentPanel", new PaymentPanel(selectedSeats, trip, (Passenger) user));
                upm.showPanelByKey("paymentPanel");
            });
            buttonPanel.add(payButton);
        }
        
        // Admin'e özel butonlar
        if (user instanceof Admin) {
            JButton viewReservationBtn = new JButton("View Reservation Information");
            viewReservationBtn.addActionListener(e -> showReservationsPopup(trip));
            styleButton(viewReservationBtn);
            
            JButton editTripBtn = new JButton("Edit Trip");
            editTripBtn.addActionListener(e -> showTripEditPopup(trip));
            styleButton(editTripBtn);
            
            JButton deleteTripBtn = new JButton("Delete Trip");
            deleteTripBtn.addActionListener(e -> {
                tripService.cancelTrip(trip);
                int result = JOptionPane.showOptionDialog(null,"Trip deleted successfully.","Trip Deleted",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"}, "Okey");
                if (result == 0) { // Okey butonuna tıklanınca:
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.setMenuBarVisible(true); 
                    upm.showPanelByKey("searching"); // SearchTripPanel’e dön
                }
            });
            styleButton(deleteTripBtn);
            
            buttonPanel.add(viewReservationBtn);
            buttonPanel.add(editTripBtn);
            buttonPanel.add(deleteTripBtn);
        }
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    //Adminin gecerli trip'i duzenlemesi icin
    private void showTripEditPopup(Trip trip) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Trip", true);
        dialog.setSize(500, 550);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(239, 228, 210));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Kalkış Tarihi - Yıl, Ay, Gün
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

        // Kalkış Saati
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Departure Time:"), gbc);
        gbc.gridx = 1;
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField hourField = new JTextField(String.valueOf(depDate.getHour()), 2);
        JTextField minuteField = new JTextField(String.valueOf(depDate.getMinute()), 2);
        timePanel.add(new JLabel("Hour:")); timePanel.add(hourField);
        timePanel.add(new JLabel("Minute:")); timePanel.add(minuteField);
        dialog.add(timePanel, gbc);

        // Ücret
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Fare:"), gbc);
        gbc.gridx = 1;
        JTextField fareField = new JTextField(String.valueOf(trip.getFare()), 10);
        dialog.add(fareField, gbc);

        // Yolculuk Süresi
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Travel Time:"), gbc);
        gbc.gridx = 1;
        JPanel tripTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField tripHourField = new JTextField(String.valueOf(trip.getTripTime().getHour()), 2);
        JTextField tripMinuteField = new JTextField(String.valueOf(trip.getTripTime().getMinute()), 2);
        tripTimePanel.add(new JLabel("Hour:")); tripTimePanel.add(tripHourField);
        tripTimePanel.add(new JLabel("Minute:")); tripTimePanel.add(tripMinuteField);
        dialog.add(tripTimePanel, gbc);

        // Kaydet butonu
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                //Fieldlerde olan bilgiler alınır ve LocalDateTime turune donusturulur.
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                LocalDateTime newDeparture = LocalDateTime.of(year, month, day, hour, minute);

                int tripHour = Integer.parseInt(tripHourField.getText());
                int tripMinute = Integer.parseInt(tripMinuteField.getText());

                //Trip bilgileri guncellenir.
                TripDTO tripDTO = new TripDTO();
                tripDTO.setDepartureDate(newDeparture);
                tripDTO.setFare(Double.parseDouble(fareField.getText()));
                tripDTO.setTripTime(LocalTime.of(tripHour, tripMinute));
                tripService.updateTrip(trip, tripDTO);

                dialog.dispose();
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Entry: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.add(saveButton, gbc);
        dialog.setVisible(true);
    }

    //Adminin rezervasyonları goruntuleyebilmesi ve iptal edebilmesi icin
    private void showReservationsPopup(Trip trip) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Rezervations", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(239, 228, 210));

        List<Reservation> reservations = trip.getReservations(); //Rezervasyonlar listede tutulur

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);

        if (!reservations.isEmpty()) {
            for (Reservation res : reservations) {
                JPanel resPanel = new JPanel(new BorderLayout());
                resPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                resPanel.setOpaque(false);
               
                String passengerInfo = res.getPassenger().getName() + " " + res.getPassenger().getSurname()+ " " + '(' + res.getPassenger().getEmail() + ')';
                JLabel infoLabel = new JLabel(passengerInfo);
                JButton cancelBtn = new JButton("Cancel");
                styleButton(cancelBtn);
                cancelBtn.addActionListener(e -> {
                    res.getTrip().deleteReservation(res);
                    int result = JOptionPane.showOptionDialog(dialog,"Reservation canceled successfully.","Canceled",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"},"Okey");
                    if (result == 0) { //Okey butonuna basılınca
                        dialog.dispose(); // Reservation popup'u kapat
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

        //Rezervasyon listesi uzun olursa diye scroll olusturulur.
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(239, 228, 210));

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    //Koltukları olusturan ve tıklanmasına izin veren metot
    private JButton createSeatButton(Seat seat, ImageIcon emptyIcon, ImageIcon bookedIcon, Trip trip) {
        JButton seatButton = new JButton();
        seatButton.setIcon(seat.getIsBooked() ? bookedIcon : emptyIcon);
        
        if(trip instanceof BusTrip){
            seatButton.setPreferredSize(new Dimension(40, 40));
        }
        else{
            seatButton.setPreferredSize(new Dimension(20, 20));
        }
        
        seatButton.setContentAreaFilled(false);
        seatButton.setBorderPainted(false);
        seatButton.setFocusPainted(false);

        if (user instanceof Passenger) {
            
            // Koltuk zaten doluysa passenger için tıklanamaz olsun, Admin hicbir sekilde tıklayamasın
            if (seat.getIsBooked()) {
                seatButton.setEnabled(false);
            } 
            else {                
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

    private void styleButton(JButton button) {
        button.setFocusPainted(true);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(200, 30));
    }
}