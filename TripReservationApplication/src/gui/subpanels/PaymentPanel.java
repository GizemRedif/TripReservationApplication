package gui.subpanels;

import gui.UserPanelManager;
import trip.model.Trip;
import seat.Seat;
import tripreservationapplication.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import user.model.Passenger;

public class PaymentPanel extends JPanel {

    public PaymentPanel(List<Seat> selectedSeats, Trip trip, Passenger passenger) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        double totalFare = selectedSeats.size() * trip.getFare();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(239, 228, 210));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel seatCountLabel = new JLabel("Seçilen Koltuk Sayısı: " + selectedSeats.size());
        JLabel totalFareLabel = new JLabel("Toplam Ücret: " + totalFare + " TL");

        seatCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalFareLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton payButton = new JButton("Öde");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setBackground(new Color(19, 29, 79));
        payButton.setForeground(Color.WHITE);

        centerPanel.add(seatCountLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(totalFareLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(payButton);

        add(centerPanel, BorderLayout.CENTER);

        // 🔘 ÖDEME butonuna tıklanınca popup açılır
        payButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(
                this,
                "Rezervasyon başarıyla oluşturuldu!",
                "Ödeme Onayı",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Tamam"},
                "Tamam"
            );

            if (result == 0) { // "Tamam" butonuna basıldıysa
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.setMenuBarVisible(true); 
                upm.showPanelByKey("searching"); // SearchTripPanel’e dön

            }
        });
    }
}
