package gui.passengerPanels;

import dto.ReservationDTO;
import gui.UserPanelManager;
import gui.components.BackButton;
import trip.model.Trip;
import seat.Seat;
import seat.BusSeat;
import seat.PlaneSeat;
import tripreservationapplication.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import reservation.service.ReservationService;
import user.model.Passenger;

public class PaymentPanel extends JPanel {

    public PaymentPanel(List<Seat> selectedSeats, Trip trip, Passenger passenger) {
        ReservationService reservationService = new ReservationService();

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        add(new BackButton("selectOrEdit"), BorderLayout.NORTH);

        // 🔢 Ekstra ücretli koltuk türlerini say
        int singleBusSeatCount = 0;
        int businessSeatCount = 0;

        for (Seat seat : selectedSeats) {
            if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                singleBusSeatCount++;
            } else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                businessSeatCount++;
            }
        }

        double baseFare = trip.getFare();
        double totalFare = 0;

        for (Seat seat : selectedSeats) {
            if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                totalFare += baseFare + 50;
            } else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                totalFare += baseFare * 2;
            } else {
                totalFare += baseFare;
            }
        }

        // 🧾 Bilgi paneli
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(239, 228, 210));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel seatCountLabel = new JLabel("Seçilen Koltuk Sayısı: " + selectedSeats.size());
        JLabel singleSeatLabel = new JLabel("Tekli Koltuk Sayısı (+50₺): " + singleBusSeatCount);
        JLabel businessSeatLabel = new JLabel("Business Class Koltuk Sayısı (×2): " + businessSeatCount);
        JLabel totalFareLabel = new JLabel("Toplam Ücret: " + totalFare + " TL");

        seatCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleSeatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        businessSeatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalFareLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton payButton = new JButton("Öde");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setBackground(new Color(19, 29, 79));
        payButton.setForeground(Color.WHITE);

        centerPanel.add(seatCountLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        if (singleBusSeatCount > 0) {
            centerPanel.add(singleSeatLabel);
            centerPanel.add(Box.createVerticalStrut(10));
        }
        if (businessSeatCount > 0) {
            centerPanel.add(businessSeatLabel);
            centerPanel.add(Box.createVerticalStrut(10));
        }
        centerPanel.add(totalFareLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(payButton);

        add(centerPanel, BorderLayout.CENTER);

        // 💳 Ödeme işlemi
        payButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(
                this,
                "Reservation created successfully!",
                "Success",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Okey"},
                "Okey"
            );

            if (result == 0) {
                for (Seat seat : selectedSeats) {
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setPassenger(passenger);

                    double seatFare = baseFare;
                    if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                        seatFare += 50;
                    } else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                        seatFare *= 2;
                    }

                    reservationDTO.setFare(seatFare);
                    reservationDTO.setSeat(seat);
                    reservationDTO.setTrip(trip);
                    reservationDTO.setReservationType(
                        trip instanceof trip.model.BusTrip ?
                            reservation.model.BusReservation.class :
                            reservation.model.FlightReservation.class
                    );
                    reservationService.createReservation(passenger, reservationDTO);
                }

                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.setMenuBarVisible(true);
                upm.showPanelByKey("searching");
            }
        });
    }
}
