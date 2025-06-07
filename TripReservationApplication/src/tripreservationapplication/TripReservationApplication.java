
package tripreservationapplication;

import javax.swing.SwingUtilities;


public class TripReservationApplication {

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    
}
