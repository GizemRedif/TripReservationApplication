package gui;

import gui.components.BackButton;
import gui.components.CreateTripCartsAndListings;
import trip.model.Trip;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.util.List;


//Aranan kriterlere uygun goruntulenen triplerin oldugu panel
public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips, User user) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Üstte geri butonu olacak.
        add(new BackButton("searching"), BorderLayout.NORTH);

        // TripsPanel içinde İcerik ve Trip Kartları olusturulur.
        CreateTripCartsAndListings listingsPanel = new CreateTripCartsAndListings(trips, user, "TripsPanel");
        add(listingsPanel, BorderLayout.CENTER);
    }
//----------------------------------------------------Contructor sonu-------------------------------------------
}
