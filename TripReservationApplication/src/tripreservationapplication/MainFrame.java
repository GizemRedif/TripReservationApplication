package tripreservationapplication;

//import gui.LoginRegisterPanel; !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
import gui.AdminPanel;
import gui.UserMainPanel;
import java.awt.Color;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Trip Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900 , 600);
        setLocationRelativeTo(null);

        // LoginRegisterPanel ekleniyor !!!!!!!!!!!!!!!!!! Ana kod bu
//        add(new LoginRegisterPanel());


//deneme amaçlı yazılanlar
    add(new UserMainPanel());
        setVisible(true);

//        add(new AdminPanel());
    }
}
