
package tripreservationapplication;

import javax.swing.SwingUtilities;


public class TripReservationApplication {

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();  // Ana GUI burada başlatılır
        });
    }
    
}
