package gui.subpanels;
        
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import seat.Seat;
import trip.model.Trip;
import user.model.User;
import user.model.Admin;

public class SeatSelectionOrTripEditPanel extends JPanel {

    private Trip trip;
    private List<Seat> seatList;
    private User user;

    public SeatSelectionOrTripEditPanel(Trip trip, User user) {
        this.trip = trip;
        this.user = user;
        this.seatList = trip.getVehicle().getSeatList();

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // 🔹 Koltuk Panelini Oluştur
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        wrapperPanel.setOpaque(false);

        JPanel seatPanel = new JPanel(new GridLayout(4, 15, 10, 10)); // 4 satır, 15 sütun
        seatPanel.setOpaque(false);

        int seatIndex = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 15; col++) {
                if (row == 1) {
                    // 🔸 Boşluk satırı
                    JPanel empty = new JPanel();
                    empty.setOpaque(false);
                    seatPanel.add(empty);
                } else {
                    if (seatIndex < seatList.size()) {
                        Seat seat = seatList.get(seatIndex++);
                        JButton seatButton = new JButton(String.valueOf(seat.getSeatNumber()));
                        seatButton.setPreferredSize(new Dimension(40, 40));
                        seatButton.setBackground(Color.LIGHT_GRAY);

                        // 🔒 Admin ise koltuk butonları pasif
                        if (user instanceof Admin) {
                            seatButton.setEnabled(false);
                        }

                        seatPanel.add(seatButton);
                    } else {
                        JPanel empty = new JPanel();
                        empty.setOpaque(false);
                        seatPanel.add(empty);
                    }
                }
            }
        }

        wrapperPanel.add(seatPanel);
        add(wrapperPanel, BorderLayout.NORTH);

        // 🔻 Alt Buton Paneli (sadece Admin ise görünür)
        if (user instanceof Admin) {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            buttonPanel.setOpaque(false);

            JButton viewReservationBtn = new JButton("Rezervasyon Bilgilerini Görüntüle");
            viewReservationBtn.addActionListener(e -> {});
            
            JButton editTripBtn = new JButton("Trip Düzenle");
            editTripBtn.addActionListener(e -> showTripEditPopup(trip));
            
            JButton deleteTripBtn = new JButton("Trip Sil");
            deleteTripBtn.addActionListener(e -> {});
            
            
            buttonPanel.add(viewReservationBtn);
            buttonPanel.add(editTripBtn);
            buttonPanel.add(deleteTripBtn);

            add(buttonPanel, BorderLayout.SOUTH);
        }
        else{
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            buttonPanel.setOpaque(false);

            JButton viewReservationBtn = new JButton("Rezervasyon Bilgilerini Görüntüle");
            viewReservationBtn.addActionListener(e -> {});
        }
    }
    
    private void showTripEditPopup(Trip trip) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Trip Düzenle", true);
        dialog.setSize(500, 550);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(239, 228, 210));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // 🔹 Kalkış Tarihi - Yıl, Ay, Gün
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Kalkış Tarihi:"), gbc);
        gbc.gridx = 1;
        LocalDateTime depDate = trip.getDepartureDate();
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField yearField = new JTextField(String.valueOf(depDate.getYear()), 4);
        JTextField monthField = new JTextField(String.valueOf(depDate.getMonthValue()), 2);
        JTextField dayField = new JTextField(String.valueOf(depDate.getDayOfMonth()), 2);
        datePanel.add(new JLabel("Yıl:")); datePanel.add(yearField);
        datePanel.add(new JLabel("Ay:")); datePanel.add(monthField);
        datePanel.add(new JLabel("Gün:")); datePanel.add(dayField);
        dialog.add(datePanel, gbc);

        // 🔹 Kalkış Saati
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Kalkış Saati:"), gbc);
        gbc.gridx = 1;
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField hourField = new JTextField(String.valueOf(depDate.getHour()), 2);
        JTextField minuteField = new JTextField(String.valueOf(depDate.getMinute()), 2);
        timePanel.add(new JLabel("Saat:")); timePanel.add(hourField);
        timePanel.add(new JLabel("Dakika:")); timePanel.add(minuteField);
        dialog.add(timePanel, gbc);

        // 🔹 Ücret
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Ücret:"), gbc);
        gbc.gridx = 1;
        JTextField fareField = new JTextField(String.valueOf(trip.getFare()), 10);
        dialog.add(fareField, gbc);

        // 🔹 Yolculuk Süresi (LocalTime -> Saat:Dakika)
        gbc.gridx = 0; gbc.gridy++;
        dialog.add(new JLabel("Yolculuk Süresi:"), gbc);
        gbc.gridx = 1;
        JPanel tripTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField tripHourField = new JTextField(String.valueOf(trip.getTripTime().getHour()), 2);
        JTextField tripMinuteField = new JTextField(String.valueOf(trip.getTripTime().getMinute()), 2);
        tripTimePanel.add(new JLabel("Saat:")); tripTimePanel.add(tripHourField);
        tripTimePanel.add(new JLabel("Dakika:")); tripTimePanel.add(tripMinuteField);
        dialog.add(tripTimePanel, gbc);

        // 🔘 Kaydet butonu
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Kaydet");
        saveButton.addActionListener(e -> {
            try {
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                LocalDateTime newDeparture = LocalDateTime.of(year, month, day, hour, minute);

                int tripHour = Integer.parseInt(tripHourField.getText());
                int tripMinute = Integer.parseInt(tripMinuteField.getText());

                trip.setDepartureDate(newDeparture);
                trip.setFare(Double.parseDouble(fareField.getText()));
                trip.setTripTime(LocalTime.of(tripHour, tripMinute));

                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Geçersiz giriş: " + ex.getMessage(),
                        "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(saveButton, gbc);

        dialog.setVisible(true);
    }


}
