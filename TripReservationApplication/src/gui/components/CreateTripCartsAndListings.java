package gui.components;

import gui.UserPanelManager;
import gui.SeatSelect_TripEditPanel;
import gui.passengerPanels.MyTripsPanel;
import reservation.model.Reservation;
import trip.model.Trip;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.Passenger;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import reservation.service.ReservationService;
import static gui.components.StyleButtons.createStyledBlueButton;
import static gui.components.StyleButtons.createStyledBrownButton;

//TripsPanel --> mevcut tripleri lstelemek için kullanılır.
//MyTriipsPanel --> Kullanıcının rezervasyonlarını listelemek için kullanılır.

public class CreateTripCartsAndListings extends JPanel {

    private List<Reservation> reservations;
    private ReservationService reservationService = new ReservationService();

    //TripsPanel kullanır.
    public CreateTripCartsAndListings(List<Trip> trips, User user, String callingPanel) {
        this(trips, null, user, callingPanel);
    }

    //MyTripsPanel kullanır. Orjinal constructordan farkı rezervasyon listesidir çünkü kullanıcı, rezervasyonlarını görüntüler.
    public CreateTripCartsAndListings(List<Trip> trips, List<Reservation> reservations, User user, String callingPanel) {
        this.reservations = reservations;

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(239, 228, 210));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if ("MyTripsPanel".equals(callingPanel)) {
            for (int i = trips.size() - 1; i >= 0; i--) {
                Trip trip = trips.get(i);
                Reservation res = (reservations != null && i < reservations.size()) ? reservations.get(i) : null;
                addTripCard(innerPanel, trip, res, user, callingPanel, timeFormatter, dateFormatter);
            }
        } 
        else { //TripsPanel ise
            for (int i = 0; i < trips.size(); i++) {
                Trip trip = trips.get(i);
                Reservation res = (reservations != null && i < reservations.size()) ? reservations.get(i) : null;
                addTripCard(innerPanel, trip, res, user, callingPanel, timeFormatter, dateFormatter);
            }
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(239, 228, 210));

        add(scrollPane, BorderLayout.CENTER);
    }
//-------------------------------------------End of constructor method-----------------------------


    //Trip Kartlarını oluşturan metot
    private void addTripCard(JPanel parent, Trip trip, Reservation res, User user, String callingPanel, DateTimeFormatter timeFormatter, DateTimeFormatter dateFormatter) {
        JPanel tripCard = new JPanel(new BorderLayout(10, 10));
        tripCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tripCard.setMaximumSize(new Dimension(500, 130));

        //Geçmiş tripler farklı gösterilir. 
        boolean isPastTrip = trip.getDepartureDate().isBefore(LocalDateTime.now());
        Color bgColor = isPastTrip ? new Color(210, 210, 210) : Color.WHITE;
        Color textColor = isPastTrip ? Color.DARK_GRAY : Color.BLACK;
        tripCard.setBackground(bgColor);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        //Departure Time----------------------------------
        JLabel timeLabel = new JLabel(trip.getDepartureDate().toLocalTime().format(timeFormatter));
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setForeground(textColor);
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(timeLabel, BorderLayout.WEST);

        //Departure Date--------------------------------
        JLabel dateLabel = new JLabel(trip.getDepartureDate().toLocalDate().format(dateFormatter));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(textColor);
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        top.add(dateLabel, BorderLayout.EAST);

        //Departure Date ve Departure Time, kartın üst kısmında olacak.
        tripCard.add(top, BorderLayout.NORTH);

        //Trip Duration and Route----------------------------------
        String tripDurationText = "---- " + trip.getTripTime() + "  ---->";
        String routeText = trip.getDepartureStation() + " " + tripDurationText + " " + trip.getArrivalStation();

        //Route bilgisi kartın merkezinde gösterilecek.
        JLabel routeLabel = new JLabel(routeText, SwingConstants.CENTER);
        routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        routeLabel.setForeground(textColor);
        tripCard.add(routeLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);

        //Fare----------------------------------------
        JLabel priceLabel = new JLabel(trip.getFare() + "₺");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setForeground(textColor);
        bottom.add(priceLabel, BorderLayout.WEST);

        //Buton-------------------------------------------
        //Eğer çağıran sınıf TripsPanel ise:
        //Admin'in Trip'i düzenlemesi için buton eklenir
        //Passenger'ın koltuk seçmesi için buton eklenir.
        if ("TripsPanel".equals(callingPanel)) {
            String buttonText = (user instanceof Admin) ? "EDIT" : "Select Seat";
            //Button (created with the static method in the StyleButtons class)
            JButton tripCartsButton = createStyledBlueButton(buttonText);

            //Admin, geçmiş tripleri görüntüler ama düzenleyemez.
            if (isPastTrip) {
                tripCartsButton.setEnabled(false);
                tripCartsButton.setToolTipText("This trip is in the past.");
            } 
            else { //Koltuk seçme ve Trip düzenleme aynı panelden yapılacak. O yüzden user kontrolüne gerek yok.
                tripCartsButton.addActionListener(e -> {
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.addPanel("selectOrEdit", new SeatSelect_TripEditPanel(trip, user));
                    upm.showPanelByKey("selectOrEdit");
                });
            }
            bottom.add(tripCartsButton, BorderLayout.EAST);
        }

        //Eğer çağıran sınıf MyTripsPanel ise:
        //Rezervasyonun iptal edilebilmesi için buton eklenir.
        if ("MyTripsPanel".equals(callingPanel) && !isPastTrip && res != null && user instanceof Passenger) {
            //Button (created with the static method in the StyleButtons class)
            JButton cancelButton = createStyledBrownButton("Cancel");

            cancelButton.addActionListener(e -> {
                reservationService.deleteReservation(res);
                reservations.remove(res);
                JOptionPane.showMessageDialog(this, "Reservation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);

                //Rezervasyon iptal edildikten sonra MyTripsPanel tekrar yüklenir. (Rezervasyon kartının kalkması için)
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("myTrips", new MyTripsPanel((Passenger) user));
                upm.showPanelByKey("myTrips");
            });

            bottom.add(cancelButton, BorderLayout.CENTER);
        }
        tripCard.add(bottom, BorderLayout.SOUTH);

        parent.add(tripCard);
        parent.add(Box.createVerticalStrut(10));
    }
}
