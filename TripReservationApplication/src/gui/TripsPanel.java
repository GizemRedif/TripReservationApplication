package gui;

import trip.model.Trip;

import javax.swing.*;
import java.awt.*;
import java.util.List;
  

//KONTROL OLMADIIIIIII


public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (trips.isEmpty()) {
            add(new JLabel("No trips found."));
            return;
        }

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

            // Later: add selectSeatButton action here
            add(tripCard);
            add(Box.createVerticalStrut(10));
        }
    }
}
