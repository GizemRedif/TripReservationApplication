package gui.components;

import gui.UserPanelManager;
import gui.SeatSelect_TripEditPanel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import trip.model.Trip;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.User;

public class CreateTripCartsAndListings extends JPanel {

    public CreateTripCartsAndListings(List<Trip> trips, User user, String callingPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(239, 228, 210));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Trip trip : trips) {
            JPanel tripCard = new JPanel(new BorderLayout(10, 10));
            tripCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            tripCard.setMaximumSize(new Dimension(500, 130));

            boolean isPastTrip = trip.getDepartureDate().isBefore(LocalDateTime.now());
            Color bgColor = isPastTrip ? new Color(210, 210, 210) : Color.WHITE;
            Color textColor = isPastTrip ? Color.DARK_GRAY : Color.BLACK;
            tripCard.setBackground(bgColor);

            // Üst panel: Sol üstte saat, sağ üstte tarih
            JPanel top = new JPanel(new BorderLayout());
            top.setOpaque(false);

            JLabel timeLabel = new JLabel(trip.getDepartureDate().toLocalTime().format(timeFormatter));
            timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            timeLabel.setForeground(textColor);
            timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
            top.add(timeLabel, BorderLayout.WEST);

            JLabel dateLabel = new JLabel(trip.getDepartureDate().toLocalDate().format(dateFormatter));
            dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            dateLabel.setForeground(textColor);
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            top.add(dateLabel, BorderLayout.EAST);

            tripCard.add(top, BorderLayout.NORTH);


            // Orta panel: Rota ve süre
            String tripDurationText = "---- " + trip.getTripTime() + "  ---->";
            String routeText = trip.getDepartureStation() + " " + tripDurationText + " " + trip.getArrivalStation();

            JLabel routeLabel = new JLabel(routeText, SwingConstants.CENTER);
            routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            routeLabel.setForeground(textColor);
            tripCard.add(routeLabel, BorderLayout.CENTER);

            // Alt panel: Fiyat + buton
            JPanel bottom = new JPanel(new BorderLayout());
            bottom.setOpaque(false);

            JLabel priceLabel = new JLabel(trip.getFare() + "₺");
            priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            priceLabel.setForeground(textColor);
            bottom.add(priceLabel, BorderLayout.WEST);

            // Buton (sadece TripsPanel'de gösterilir)
            if ("TripsPanel".equals(callingPanel)) {
                String buttonText = (user instanceof Admin) ? "EDIT" : "Select Seat";
                JButton selectSeatButton = new JButton(buttonText);
                styleSelectOrEditButton(selectSeatButton);

                if (isPastTrip) {
                    selectSeatButton.setEnabled(false);
                    selectSeatButton.setToolTipText("Bu sefer geçmişte kaldı.");
                } else {
                    selectSeatButton.addActionListener(e -> {
                        UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                        upm.addPanel("selectOrEdit", new SeatSelect_TripEditPanel(trip, user));
                        upm.showPanelByKey("selectOrEdit");
                    });
                }

                bottom.add(selectSeatButton, BorderLayout.EAST);
            }

            tripCard.add(bottom, BorderLayout.SOUTH);

            innerPanel.add(tripCard);
            innerPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(239, 228, 210));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void styleSelectOrEditButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
}
