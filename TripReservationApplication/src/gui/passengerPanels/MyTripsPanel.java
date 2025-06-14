package gui.passengerPanels;

import gui.components.CreateTripCartsAndListings;
import reservation.model.Reservation;
import reservation.service.ReservationService;
import trip.model.BusTrip;
import trip.model.FlightTrip;
import trip.model.Trip;
import user.model.Passenger;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Passenger'ın rezervasyonlarını görüntülediği ve iptal edebildiği panel.
public class MyTripsPanel extends JPanel {

    ReservationService reservationService = new ReservationService();

    public MyTripsPanel(Passenger passenger) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Tüm rezervasyonları al ve trip türüne (Bus or Flight) göre ayır
        List<Reservation> allReservations = reservationService.getReservationsByUser(passenger);
        List<Trip> busTrips = new ArrayList<>(); //Bus Triplerinin listesi
        List<Reservation> busReservations = new ArrayList<>(); //Bus rezervasyonlarının listesi
        List<Trip> flightTrips = new ArrayList<>(); //Flight triplerinin listesi
        List<Reservation> flightReservations = new ArrayList<>(); //Flight rezervasyonlarının listesi

        for (Reservation res : allReservations) {
            Trip trip = res.getTrip();
            
            if (trip instanceof BusTrip) {
                busTrips.add(trip);
                busReservations.add(res);
            } 
            else if (trip instanceof FlightTrip) {
                flightTrips.add(trip);
                flightReservations.add(res);
            }
        }

        // Başlık
        JLabel titleLabel = new JLabel("My Trips");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Sekmeler (Bus and Flight)
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabbedPane.addTab("Bus", createTripScrollPanel(busTrips, busReservations, passenger));
        tabbedPane.addTab("Flight", createTripScrollPanel(flightTrips, flightReservations, passenger));

        add(tabbedPane, BorderLayout.CENTER);
    }
//-------------------------------------------End of constructor method-----------------------------
    
    //Kart listesi uzun olursa scroll oluşur.
    private JScrollPane createTripScrollPanel(List<Trip> trips, List<Reservation> reservations, Passenger passenger) {
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(new Color(239, 228, 210));

        //Trip listesi boş ise rezervasyon olmadığı söylenir.
        if (trips.isEmpty()) {
            JLabel noTripsLabel = new JLabel("There is no reservation.");
            noTripsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            noTripsLabel.setForeground(Color.DARK_GRAY);
            noTripsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            noTripsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            wrapperPanel.add(noTripsLabel);
        } 
        //Trip listesi boş değilse CreateTripCartsAndListings sınıfı ile kartlar oluşturulur ve listelenir.
        else {
            wrapperPanel.add(new CreateTripCartsAndListings(trips, reservations, passenger, "MyTripsPanel"));
        }

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(new Color(239, 228, 210));
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        return scrollPane;
    }
}