package gui;

import trip.model.Trip;
import user.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import tripreservationapplication.MainFrame;

public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips, User user) {
        setLayout(new BorderLayout());

        // 🔹 Üst panel: Geri butonu
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(new Color(220, 220, 220));
        JButton backButton = new JButton("← Back");

        backButton.addActionListener(e -> {
    MainFrame.getInstance().showSearchTripPanel(user);
});


        topBar.add(backButton);
        add(topBar, BorderLayout.NORTH);

        // 🔹 İçerik: Trip kartları
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        for (Trip trip : trips) {
            JPanel tripCard = new JPanel(new GridLayout(0, 1));
            tripCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            tripCard.setBackground(new Color(250, 250, 250));
            tripCard.setMaximumSize(new Dimension(500, 100));

            tripCard.add(new JLabel("From: " + trip.getDepartureStation()));
            tripCard.add(new JLabel("To: " + trip.getArrivalStation()));
            tripCard.add(new JLabel("Date: " + trip.getDepartureDate().toString()));
            tripCard.add(new JLabel("Price: " + trip.getFare() + "₺"));

            JButton selectSeatButton = new JButton("Select Seat");
            tripCard.add(selectSeatButton);

            innerPanel.add(tripCard);
            innerPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }
}
