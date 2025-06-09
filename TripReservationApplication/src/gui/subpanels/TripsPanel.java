package gui.subpanels;

import gui.UserPanelManager;
import gui.components.TripCartsAndListings;
import gui.subpanels.SeatSelectionOrTripEditPanel;
import trip.model.Trip;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.Passenger;

public class TripsPanel extends JPanel {

    public TripsPanel(List<Trip> trips, User user) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        
        //--------------Üst panel: Geri ve Filtrele butonları---------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(19, 29, 79));

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> {
            UserPanelManager panelManager = (UserPanelManager) MainFrame.getInstance().getContentPane();
            panelManager.setMenuBarVisible(true);
            panelManager.showPanelByKey("searching");
        });

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(backButton);
        
        JButton filterButton = new JButton("🔍 Filter");
        filterButton.addActionListener(e -> showFilterPopup());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(filterButton);

        topBar.add(leftPanel, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // TripsPanel içinde: İcerik ve Trip Kartları olusturulur.
        TripCartsAndListings listingsPanel = new TripCartsAndListings(trips, user, "TripsPanel");
        add(listingsPanel, BorderLayout.CENTER);


    }
//----------------------------------------------------Contructor sonu-------------------------------------------
    
    
    //Filtreleme pop-up'ı acılacak ve istenilen filtreler yapılacak.
    private void showFilterPopup() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Filter Trips", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Buraya filtre seçenekleri gelecek..."));

        dialog.add(panel);
        dialog.setVisible(true);
    }
    
   
}
