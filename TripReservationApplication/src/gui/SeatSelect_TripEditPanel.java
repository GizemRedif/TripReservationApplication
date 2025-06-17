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

//Passenger'ın mkoltuk seçebileceği, Admin'in tripi ve rezervasyonlarını güzenleyebileceği panel.
public class SeatSelect_TripEditPanel extends JPanel {

    private List<Seat> seatList;
    private User user;
    private List<Seat> selectedSeats = new ArrayList<>();
    TripService tripService = new TripService();
    ReservationService reservationService = new ReservationService();

    public SeatSelect_TripEditPanel(Trip trip, User user) {
        this.user = user;
        this.seatList = trip.getVehicle().getSeatList();  //Secilen trip'e ait koltuklar listelenir.
                
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));      

        // Koltuk resimleri yüklenir
        ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeatForPlane.jpg"));
        ImageIcon bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeatForPlane.jpg"));
        
        //Bus ve Plane koltuklarının pixelleri farklı oldugu icin koltuk resimlerini farklı boyutlarda ekliyorum
        if(trip instanceof BusTrip){ 
            emptyIcon = new ImageIcon(getClass().getResource("/gui/pictures/emptySeat.jpg"));
            bookedIcon = new ImageIcon(getClass().getResource("/gui/pictures/bookedSeat.jpg"));
        }

        //Koltukların gosterilecegi panel oluşturulur (Koltuk paneli)
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        wrapperPanel.setOpaque(false);

        JPanel seatPanel; // koltuklar bu panele gore eklenecek. O nedenle bus ve flight icin ayrı olmalı

        //BusTrip ve FligtTrip koltuk düzeni farklı.
        if (trip instanceof BusTrip) {
            seatPanel = new JPanel(new GridLayout(4, 15, 10, 10)); //4 satır ve 15 sütun bulunmakta.
            seatPanel.setOpaque(false);

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 15; col++) {
                    if (row == 1) { 
                        //Bu satıra, koridor'u temsil edeceği için buton eklenmez.
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } 
                    else {
                        int index;
                        //row sırasına göre index değiştirilir.
                        index = switch (row) {
                            case 0 -> col * 3;
                            case 2 -> col * 3 + 1;
                            default -> col * 3 + 2; //row == 3
                        };
                        
                        //index numaralı koltuğa ait buton oluşturulur.
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
            seatPanel = new JPanel(new GridLayout(7, 25, 10, 10)); //7 satır ve 25 sütun olacak.
            seatPanel.setOpaque(false);

            // Satır harfleri (4. sıra boşluk olacak çünkü koridoru temsil edecek)
            char[] seatRowChars = {'A', 'B', 'C', '-', 'D', 'E', 'F'};

            for (char rowChar : seatRowChars) {
                for (int col = 1; col <= 25; col++) {
                    //Koridor'u temsil eden sıraya koltuk eklenmeyecek.
                    if (rowChar == '-') { 
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    } 
                    else {
                        String seatNumber = col + "" + rowChar;

                        // seatList'ten doğru seat'i bul
                        Seat seat = null;
                        for (Seat s : seatList) {
                            if (s.getSeatNumber().equals(seatNumber)) {
                                seat = s;
                                break;
                            }
                        }
                        
                        //Seat'i temsil eden buton ve seat adının yer aldığı label oluşturulur.
                        if (seat != null) {
                            JButton seatButton = createSeatButton(seat, emptyIcon, bookedIcon, trip);

                            //Seat button + seat name label
                            JPanel seatWithLabel = new JPanel();
                            seatWithLabel.setLayout(new BoxLayout(seatWithLabel, BoxLayout.Y_AXIS));
                            seatWithLabel.setOpaque(false);
                            seatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                            //Seat adını gösteren label
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
            //Trip türüne göre bilgilendirmeler gösterilir.
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
                //Eğer seçili koltuk yoksa PaymentPanel'e gidilmez.
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
        
        // Admin'e özel butonlar
        if (user instanceof Admin) {
            //Buttons (created with the static method in the StyleButtons class)
            
            //Rezervasyon bilgisini görüntüleyip rezervasyonları silebileceği popup için buton.
            JButton viewReservationBtn = createStyledBlueButton("View Reservation Information");
            viewReservationBtn.addActionListener(e -> showReservationsPopup(trip));
            
            //Trip'i düzenleyebileceği popup için buton
            JButton editTripBtn = createStyledBlueButton("Edit Trip");
            editTripBtn.addActionListener(e -> showTripEditPopup(trip));
            
            //Trip'i silmesi için buton
            JButton deleteTripBtn = createStyledBlueButton("Delete Trip");
            deleteTripBtn.addActionListener(e -> {
                tripService.cancelTrip(trip);
                int result = JOptionPane.showOptionDialog(null,"Trip deleted successfully.","Trip Deleted",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"}, "Okey");
                if (result == 0) { // Okey butonuna tıklanınca:
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.setMenuBarVisible(true); 
                    upm.showPanelByKey("searching"); // SearchTripPanel’e dön
                }
            });
            
            buttonPanel.add(viewReservationBtn);
            buttonPanel.add(editTripBtn);
            buttonPanel.add(deleteTripBtn);
        }
        add(buttonPanel, BorderLayout.SOUTH);
    }
//-------------------------------------------End of constructor method-----------------------------

    //Adminin gecerli trip'i duzenlemesi icin popup
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

        // Kalkış Saati - Hour, Minute
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
                //Arrival ve Departurre station değişmediği için eski bilgiden çekilir.
                tripDTO.setDepartureStation(trip.getDepartureStation());
                tripDTO.setArrivalStation(trip.getArrivalStation());
                tripDTO.setDepartureDate(newDeparture);
                tripDTO.setFare(Double.parseDouble(fareField.getText()));
                tripDTO.setTripTime(LocalTime.of(tripHour, tripMinute));
                tripService.updateTrip(trip, tripDTO);
                
                dialog.dispose();
                
                int result1 = JOptionPane.showOptionDialog(null,"Trip edited successfully.","Trip Edited",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"}, "Okey");
                if (result1 == 0) { // Okey butonuna tıklanınca:
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.setMenuBarVisible(true); 
                    upm.showPanelByKey("searching"); // SearchTripPanel’e dön
                }                
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Entry: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.add(saveButton, gbc);
        dialog.setVisible(true);
    }

    //Adminin rezervasyonları goruntuleyebilmesi ve iptal edebilmesi icin popup
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

        //Eğer rezervasyon varsa listelenir, yoksa olmadığı bilgisi verilir.
        if (!reservations.isEmpty()) {
            for (Reservation res : reservations) {
                JPanel resPanel = new JPanel(new BorderLayout());
                resPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                resPanel.setOpaque(false);
               
                //Rezervasyon bilgileri kullanıcı adı, soyadı ve e-maili ile gösterilir.
                String passengerInfo = res.getPassenger().getName() + " " + res.getPassenger().getSurname()+ " " + '(' + res.getPassenger().getEmail() + ')';
                JLabel infoLabel = new JLabel(passengerInfo);
                
                //Rezervasyonun iptal edilebilmesi için buton (created with the static method in the StyleButtons class)
                JButton cancelBtn = createStyledBrownButton("Cancel");
                cancelBtn.addActionListener(e -> {
                    reservationService.deleteReservation(res);
                    reservations.remove(res);
                
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
        
        //Bus ve Plane için gösterilen koltuk boyuları farklı. 
        if(trip instanceof BusTrip){
            seatButton.setPreferredSize(new Dimension(40, 40));
        }
        else{
            seatButton.setPreferredSize(new Dimension(20, 20));
        }
        
        seatButton.setContentAreaFilled(false);
        seatButton.setBorderPainted(false);
        seatButton.setFocusPainted(false);
        
        // Koltuk zaten doluysa passenger için tıklanamaz olsun, Admin hicbir sekilde tıklayamasın
        if (user instanceof Passenger) {
            if (seat.getIsBooked()) {
                seatButton.setEnabled(false);
            } 
            else {                
                //Butona her tıklandığında koltuğun seçili olma durumu değişir ve o anki duruma uygun görsel gösterilir
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