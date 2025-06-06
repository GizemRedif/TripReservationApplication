package gui;

import model.Trip;
import model.Seat;
import javax.swing.*;
import java.awt.*;
import java.util.List;



//KONTROL OLMADI DİREKT GPTDEN YAPISTIRDIM







public class SeatSelectionPanel extends JPanel {

    private JButton payButton;
    private Seat selectedSeat;

    public SeatSelectionPanel(Trip trip) {
        setLayout(new GridLayout(10, 4, 10, 10)); // 10 sıra, 2+1 düzende oturur
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        List<Seat> seats = trip.getSeats(); // Koltuklar veriyle dolu

        for (Seat seat : seats) {
            JButton seatBtn = new JButton(String.valueOf(seat.getNumber()));
            seatBtn.setOpaque(true);
            seatBtn.setBorderPainted(false);
            seatBtn.setPreferredSize(new Dimension(40, 40));

            if (seat.isOccupied()) {
                seatBtn.setEnabled(false);
                seatBtn.setBackground(seat.getGender() == 'M' ? Color.BLUE : Color.PINK);
            } else {
                seatBtn.setBackground(Color.LIGHT_GRAY);
                seatBtn.addActionListener(e -> {
                    resetSelection();
                    seatBtn.setBackground(Color.GREEN);
                    selectedSeat = seat;
                    if (payButton != null) payButton.setEnabled(true);
                });
            }

            add(seatBtn);
        }
    }

    private void resetSelection() {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (btn.isEnabled()) {
                    btn.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    public Seat getSelectedSeat() {
        return selectedSeat;
    }

    public void setPayButton(JButton button) {
        this.payButton = button;
    }
}
