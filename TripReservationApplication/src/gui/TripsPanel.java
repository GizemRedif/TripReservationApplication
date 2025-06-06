package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Trip;







//KONTROL OLMADI DİREKT GPTDEN YAPISTIRDIM







public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> tripList) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240));

        for (Trip trip : tripList) {
            add(createTripCard(trip));
        }
    }

    private JPanel createTripCard(Trip trip) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(600, 80));

        JLabel info = new JLabel(String.format("Saat: %s | Tarih: %s | Fiyat: %.2f₺",
                trip.getDepartureTime(), trip.getDepartureDate(), trip.getFare()));
        info.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton seatButton = new JButton("Koltuk Seç");
        seatButton.addActionListener(e -> openSeatDialog(trip));

        panel.add(info, BorderLayout.CENTER);
        panel.add(seatButton, BorderLayout.EAST);

        return panel;
    }

    private void openSeatDialog(Trip trip) {
        JDialog seatDialog = new JDialog((Frame) null, "Koltuk Seçimi", true);
        seatDialog.setSize(500, 400);
        seatDialog.setLocationRelativeTo(null);
        seatDialog.setLayout(new BorderLayout());

        SeatSelectionPanel seatPanel = new SeatSelectionPanel(trip);
        JButton payButton = new JButton("Ödemeye Geç");
        payButton.setEnabled(false);

        seatPanel.setPayButton(payButton);

        payButton.addActionListener(e -> {
            seatDialog.dispose();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new PaymentPanel(trip, seatPanel.getSelectedSeat()));
            topFrame.revalidate();
            topFrame.repaint();
        });

        seatDialog.add(seatPanel, BorderLayout.CENTER);
        seatDialog.add(payButton, BorderLayout.SOUTH);
        seatDialog.setVisible(true);
    }
}
