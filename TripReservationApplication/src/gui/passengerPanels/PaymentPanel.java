package gui.passengerPanels;

import dto.ReservationDTO;
import gui.UserPanelManager;
import gui.components.BackButton;
import static gui.components.StyleButtons.createStyledBlueButton;
import trip.model.Trip;
import seat.Seat;
import seat.BusSeat;
import seat.PlaneSeat;
import tripreservationapplication.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import reservation.model.BusReservation;
import reservation.model.FlightReservation;
import reservation.service.ReservationService;
import trip.model.BusTrip;
import user.model.Passenger;

//The panel where the user comes to make payment after selecting the seats.
public class PaymentPanel extends JPanel {
    ReservationService reservationService = new ReservationService();
    
    public PaymentPanel(List<Seat> selectedSeats, Trip trip, Passenger passenger) {

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        //If you click the Back button, you will return to the SeatSelect_TripEditPanel.
        add(new BackButton("selectOrEdit"), BorderLayout.NORTH);

        // Count the types of seats with extra charges
        int singleBusSeatCount = 0; //Number of single seats selected on the bus trip
        int businessSeatCount = 0; //Number of business class seats selected on the flight trip
        for (Seat seat : selectedSeats) {
            if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                singleBusSeatCount++;
            } 
            else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                businessSeatCount++;
            }
        }

        double baseFare = trip.getFare(); //Trip price before the price of extra paid seats is added
        double totalFare = 0; //Total price to be paid (Price of extra paid seats and will vary for multiple seats)
        for (Seat seat : selectedSeats) {
            if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                totalFare += baseFare + 50;
            } 
            else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                totalFare += baseFare * 2;
            } 
            else {
                totalFare += baseFare;
            }
        }

        //Information panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(239, 228, 210));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel seatCountLabel = new JLabel("Number of Selected Seats: " + selectedSeats.size());
        JLabel singleSeatLabel = new JLabel("Number of Sİngle Seats (+50₺): " + singleBusSeatCount);
        JLabel businessSeatLabel = new JLabel("Number of Business Class Seats (×2₺): " + businessSeatCount);
        JLabel totalFareLabel = new JLabel("Total Fare: " + totalFare + " TL");

        seatCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleSeatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        businessSeatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalFareLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //If an extra paid seat is selected, it will be indicated and its number will be stated.
        centerPanel.add(seatCountLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        if (singleBusSeatCount > 0) {
            centerPanel.add(singleSeatLabel);
            centerPanel.add(Box.createVerticalStrut(10));
        }
        if (businessSeatCount > 0) {
            centerPanel.add(businessSeatLabel);
            centerPanel.add(Box.createVerticalStrut(10));
        }
        centerPanel.add(totalFareLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        
        //Button (created with the static method in the StyleButtons class)
        JButton payButton = createStyledBlueButton("Payment");

        //When the payButton is clicked, the payment is made.
        payButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(this,"Reservation created successfully!","Success",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"},"Okey");

            //When the okey button is pressed in the pop-up window, a reservation is made for the selected seats in order.
            if (result == 0) {
                for (Seat seat : selectedSeats) {
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setPassenger(passenger);

                    //If the seat has an extra charge, the final fee is recorded in the reservation information.
                    double seatFare = baseFare;
                    if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                        seatFare += 50;
                    } 
                    else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                        seatFare *= 2;
                    }
                    reservationDTO.setFare(seatFare);
                    reservationDTO.setSeat(seat);
                    reservationDTO.setTrip(trip);
                    reservationDTO.setReservationType(trip instanceof BusTrip ? BusReservation.class : FlightReservation.class);
                    reservationService.createReservation(passenger, reservationDTO);
                }
                //After payment is made, you will be returned to SearchTripPanel.
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.setMenuBarVisible(true);
                upm.showPanelByKey("searching");
            }
        });
        centerPanel.add(payButton);
        add(centerPanel, BorderLayout.CENTER);
    }
//-------------------------------------------End of constructor method-----------------------------
}
