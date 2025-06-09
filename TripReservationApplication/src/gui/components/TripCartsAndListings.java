
package gui.components;

import gui.UserPanelManager;
import gui.subpanels.SeatSelectionOrTripEditPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import trip.model.Trip;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.User;

//TripsPanel -> uygun Tripleri listelemek icin kullanır.

public class TripCartsAndListings extends JPanel {
    
    public TripCartsAndListings(List<Trip> trips, User user, String callingPanel){
                                                             //Hangi panel cagırıyor onu yazarız. TripsPanel ya da PastTrips
        
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));
        
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
            
            
            if("TripsPanel".equals(callingPanel)){
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
                
            }
           
            
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
    
    
        private void styleSelectOrEditButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
}
