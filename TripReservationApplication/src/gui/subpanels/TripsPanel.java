package gui.subpanels;

import gui.components.BackButton;
import gui.components.TripCartsAndListings;
import trip.model.Trip;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips, User user) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Üstte geri butonları
        add(new BackButton("searching"), BorderLayout.NORTH);


        // TripsPanel içinde: İcerik ve Trip Kartları olusturulur.
        TripCartsAndListings listingsPanel = new TripCartsAndListings(trips, user, "TripsPanel");
        add(listingsPanel, BorderLayout.CENTER);


    }
//----------------------------------------------------Contructor sonu-------------------------------------------
    
    

    
   
}
