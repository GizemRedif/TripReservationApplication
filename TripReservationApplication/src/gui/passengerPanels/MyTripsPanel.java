package gui.passengerPanels;

import gui.components.CreateTripCartsAndListings;
import trip.model.Trip;
import trip.service.TripService;
import user.model.User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import reservation.model.Reservation;
import reservation.service.ReservationService;
import user.model.Passenger;

public class MyTripsPanel extends JPanel {

    public MyTripsPanel(Passenger passenger) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        TripService tripService = new TripService();
        ReservationService reservationService = new ReservationService();
        List<Reservation> allTrips = reservationService.getReservationsByUser(passenger);

        // Yolculukları zamana göre ayır
        LocalDateTime now = LocalDateTime.now();
//        List<Reservation> pastTrips = allTrips.stream()
//                .filter(trip -> trip.getDepartureDate().isBefore(now))
//                .collect(Collectors.toList());
//
//        List<Reservation> futureTrips = allTrips.stream()
//                .filter(trip -> !trip.getDepartureDate().isBefore(now))
//                .collect(Collectors.toList());

        // İçerik paneli
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(239, 228, 210));

        // 🔹 GELECEK YOLCULUKLAR
        JLabel upcomingLabel = new JLabel("Upcoming Trips");
        upcomingLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        upcomingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(upcomingLabel);
        contentPanel.add(Box.createVerticalStrut(10));
//        contentPanel.add(new TripCartsAndListings(futureTrips, passenger, "MyTripsPanel"));

        contentPanel.add(Box.createVerticalStrut(30)); // boşluk

        // 🔸 GEÇMİŞ YOLCULUKLAR
        JLabel pastLabel = new JLabel("Past Trips");
        pastLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pastLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(pastLabel);
        contentPanel.add(Box.createVerticalStrut(10));
//        contentPanel.add(new TripCartsAndListings(pastTrips, passenger, "MyTripsPanel"));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }
}
