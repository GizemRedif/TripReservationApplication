package gui.components;

import gui.UserPanelManager;
import gui.SeatSelect_TripEditPanel;
import gui.passengerPanels.MyTripsPanel;
import reservation.model.Reservation;
import trip.model.Trip;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import reservation.service.ReservationService;

public class CreateTripCartsAndListings extends JPanel {

    private List<Reservation> reservations;
    private ReservationService reservationService = new ReservationService();

    public CreateTripCartsAndListings(List<Trip> trips, User user, String callingPanel) {
        this(trips, null, user, callingPanel);
    }

    public CreateTripCartsAndListings(List<Trip> trips, List<Reservation> reservations, User user, String callingPanel) {
        this.reservations = reservations;

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(239, 228, 210));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if ("MyTripsPanel".equals(callingPanel)) {
            for (int i = trips.size() - 1; i >= 0; i--) {
                Trip trip = trips.get(i);
                Reservation res = (reservations != null && i < reservations.size()) ? reservations.get(i) : null;
                addTripCard(innerPanel, trip, res, user, callingPanel, timeFormatter, dateFormatter);
            }
        } else {
            for (int i = 0; i < trips.size(); i++) {
                Trip trip = trips.get(i);
                Reservation res = (reservations != null && i < reservations.size()) ? reservations.get(i) : null;
                addTripCard(innerPanel, trip, res, user, callingPanel, timeFormatter, dateFormatter);
            }
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(239, 228, 210));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void addTripCard(JPanel parent, Trip trip, Reservation res, User user, String callingPanel, DateTimeFormatter timeFormatter, DateTimeFormatter dateFormatter) {
        JPanel tripCard = new JPanel(new BorderLayout(10, 10));
        tripCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tripCard.setMaximumSize(new Dimension(500, 130));

        boolean isPastTrip = trip.getDepartureDate().isBefore(LocalDateTime.now());
        Color bgColor = isPastTrip ? new Color(210, 210, 210) : Color.WHITE;
        Color textColor = isPastTrip ? Color.DARK_GRAY : Color.BLACK;
        tripCard.setBackground(bgColor);

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

        String tripDurationText = "---- " + trip.getTripTime() + "  ---->";
        String routeText = trip.getDepartureStation() + " " + tripDurationText + " " + trip.getArrivalStation();

        JLabel routeLabel = new JLabel(routeText, SwingConstants.CENTER);
        routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        routeLabel.setForeground(textColor);
        tripCard.add(routeLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);

        JLabel priceLabel = new JLabel(trip.getFare() + "₺");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setForeground(textColor);
        bottom.add(priceLabel, BorderLayout.WEST);

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

        if ("MyTripsPanel".equals(callingPanel) && !isPastTrip && res != null && user instanceof Passenger) {
            JButton cancelButton = new JButton("İptal Et");
            styleCancelButton(cancelButton);

            cancelButton.addActionListener(e -> {
                reservationService.deleteReservation(res);
                reservations.remove(res);
                JOptionPane.showMessageDialog(this, "Rezervasyon iptal edildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("myTrips", new MyTripsPanel((Passenger) user));
                upm.showPanelByKey("myTrips");
            });

            bottom.add(cancelButton, BorderLayout.CENTER);
        }

        tripCard.add(bottom, BorderLayout.SOUTH);

        parent.add(tripCard);
        parent.add(Box.createVerticalStrut(10));
    }

    private void styleSelectOrEditButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private void styleCancelButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(180, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
}
