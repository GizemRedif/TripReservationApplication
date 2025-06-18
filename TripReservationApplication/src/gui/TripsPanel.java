package gui;

import gui.components.BackButton;
import gui.components.CreateTripCartsAndListings;
import trip.model.Trip;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.util.List;


//Panel with trips that match the search criteria
public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips, User user) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        //There will be a back button at the top.
        add(new BackButton("searching"), BorderLayout.NORTH);

        // Content and Trip Cards are created in TripsPanel.
        CreateTripCartsAndListings listingsPanel = new CreateTripCartsAndListings(trips, user, "TripsPanel");
        add(listingsPanel, BorderLayout.CENTER);
    }
//----------------------------------------------------Contructor sonu-------------------------------------------
}
