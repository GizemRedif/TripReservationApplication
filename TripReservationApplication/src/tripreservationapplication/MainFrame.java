package tripreservationapplication;

import gui.LoginRegisterPanel;
import gui.UserPanelManager;   
import javax.swing.*;
import user.model.User;       

public class MainFrame extends JFrame {

    private static MainFrame instance;
    
    public MainFrame() {
        instance = this;  //Singleton
        setTitle("Trip Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900 , 600);
        setLocationRelativeTo(null);
  
        //Show login panel at startup, no menu bar
        setJMenuBar(null);
        setContentPane(new LoginRegisterPanel());

        setVisible(true);
    } 
//-------------------------------------------End of constructor method-----------------------------

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
    
    //The method to be called when the login is successful (it will call the Login method in LoginRegisterPanel).
    //Thus, the panels and MenuBar will be arranged according to the logged in user.
    public void showUserPanelManager(User user) {
        UserPanelManager userPanel = new UserPanelManager(user); 
        setJMenuBar(userPanel.getMenuBar());
        setContentPane(userPanel);
        revalidate();
        repaint();
    }
}
