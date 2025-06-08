package gui;

import trip.model.Trip;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import tripreservationapplication.MainFrame;
import user.model.Admin;
//import user.model.Passenger;

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

        
        //-----------------------İçerik: Trip kartları----------------------------
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(239, 228, 210));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Trip trip : trips) {
            JPanel tripCard = new JPanel(new BorderLayout(10, 10));
            tripCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            tripCard.setBackground(Color.WHITE);
            tripCard.setMaximumSize(new Dimension(500, 130));

            // 🕒 Üst kısım: saat
            JLabel timeLabel = new JLabel(trip.getDepartureDate().toLocalTime().format(timeFormatter));
            timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            top.setOpaque(false);
            top.add(timeLabel);
            tripCard.add(top, BorderLayout.NORTH);

            // ➡️ Ortadaki rota
            JLabel routeLabel = new JLabel(trip.getDepartureStation() + " -----> " + trip.getArrivalStation(), SwingConstants.CENTER);
            routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            tripCard.add(routeLabel, BorderLayout.CENTER);

            // 💰 Alt kısım: fiyat
            JPanel bottom = new JPanel(new BorderLayout());
            bottom.setOpaque(false);
            JLabel priceLabel = new JLabel("Price: " + trip.getFare() + "₺");
            priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            bottom.add(priceLabel, BorderLayout.WEST);

            //Alt kısım:  Buton (User kontrolu yapılır ve buton yazısı belirlenir.)
            String buttonText = "Select Seat";
            if(user instanceof Admin){buttonText = "EDIT";}
            JButton selectSeatButton = new JButton(buttonText);
            styleSelectOrEditButton(selectSeatButton);
            bottom.add(selectSeatButton, BorderLayout.EAST);
            selectSeatButton.addActionListener(e ->{
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("selectOrEdit", new SeatSelectionOrTripEditPanel(trip, user));  // paneli CardLayout’a ekle
                upm.showPanelByKey("selectOrEdit");                            // geçiş yap
            });
            
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

    private void styleSelectOrEditButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    
   
}
