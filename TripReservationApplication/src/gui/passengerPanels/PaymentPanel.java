package gui.passengerPanels;

import dto.ReservationDTO;
import gui.UserPanelManager;
import gui.components.BackButton;
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

//Kullanıcının, koltukları seçtikten sonra ödeme yapması için geldiği panel.
public class PaymentPanel extends JPanel {
    ReservationService reservationService = new ReservationService();
    
    public PaymentPanel(List<Seat> selectedSeats, Trip trip, Passenger passenger) {

        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        //Back butonuna tıklanırsa SeatSelect_TripEditPanel'e geri dönülür.
        add(new BackButton("selectOrEdit"), BorderLayout.NORTH);

        // Count the types of seats with extra charges
        int singleBusSeatCount = 0; //Bus trip'te seçilen tekli koltuk sayısı
        int businessSeatCount = 0; //Flight trip'te seçilen bussiness class koltuk sayısı
        for (Seat seat : selectedSeats) {
            if (seat instanceof BusSeat && ((BusSeat) seat).isIsSingle()) {
                singleBusSeatCount++;
            } 
            else if (seat instanceof PlaneSeat && ((PlaneSeat) seat).isIsBussinessClass()) {
                businessSeatCount++;
            }
        }

        double baseFare = trip.getFare(); //Ekstra ücretli koltukların fiyatı eklenmeden önceki trip ücreti
        double totalFare = 0; //Ödenecek toplam ücret (Eksrta ücretli koltuk fiyatları ve birden çok koltuk için değişecek)
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

        //Bilgi paneli
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

        //Eğer ekstra ücretli koltuk seçildiyse belirtilir ve sayısı söylenir.
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
        
        JButton payButton = new JButton("Payment");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setBackground(new Color(19, 29, 79));
        payButton.setForeground(Color.WHITE);

        //payButton'a tıklanınca ödeme işlemi yapılır.
        payButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(this,"Reservation created successfully!","Success",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{"Okey"},"Okey");

            //Açılan popup'ta okey butonuna basıldığında seçili koltuklar için sırayla rezervasyon yapılır
            if (result == 0) {
                for (Seat seat : selectedSeats) {
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setPassenger(passenger);

                    //Koltuk esktra ücretli ise son ücreti, rezervasyon bilgisine kaydedilir.
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
                //Ödeme yapıldıktan sonra SearchTripPanel'e geri dönülür.
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
