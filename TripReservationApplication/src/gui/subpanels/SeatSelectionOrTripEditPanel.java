package gui.subpanels;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import seat.Seat;

import trip.model.Trip;
import user.model.User;

public class SeatSelectionOrTripEditPanel extends JPanel {

    private Trip trip;
    private List<Seat> seatList;

    public SeatSelectionOrTripEditPanel(Trip trip, User user) {
        this.trip = trip;
        this.seatList = trip.getVehicle().getSeatList();

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(3, 15, 10, 10)); // 3 satır, 15 sütun

        int seatIndex = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 15; col++) {
                if (row == 1) {
                    // Orta koltuktan önce boşluk için panel ekleniyor
                    JPanel emptySpace = new JPanel();
                    emptySpace.setOpaque(false);
                    seatPanel.add(emptySpace);
                }

                if (seatIndex < seatList.size()) {
                    Seat seat = seatList.get(seatIndex++);
                    JButton seatButton = new JButton(String.valueOf(seat.getSeatNumber()));
                    seatButton.setPreferredSize(new Dimension(40, 40));
                    seatButton.setBackground(Color.LIGHT_GRAY);
                    seatPanel.add(seatButton);
                } else {
                    // Yeterli koltuk kalmadıysa boş panel ekle
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setOpaque(false);
                    seatPanel.add(emptyPanel);
                }
            }
        }

        add(seatPanel, BorderLayout.CENTER);
    }
}
