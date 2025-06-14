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
  
        //Başlangıçta login paneli göster, menü bar yok
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
    
    //Giriş başarılı olunca çağrılacak metod (LoginRegisterPanel'de Login metodu cagıracak). 
    //Boylece giris yapan kullanıcıya gore paneller ve MenuBar duzenlenecek.
    public void showUserPanelManager(User user) {
        UserPanelManager userPanel = new UserPanelManager(user); 
        setJMenuBar(userPanel.getMenuBar());
        setContentPane(userPanel);
        revalidate();
        repaint();
    }
}
