package gui.components;

import gui.UserPanelManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import tripreservationapplication.MainFrame;

//Kullanan classlar: 
//SeatSelect_TripEditPanel
//PaymentPanel 

public class BackButton extends JPanel{
    
    public BackButton(String previousPanel){
        
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));
        
        //--------------Üst panel: Geri butonu---------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(19, 29, 79));

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> {
            UserPanelManager panelManager = (UserPanelManager) MainFrame.getInstance().getContentPane();
            //Eğer SearchTripPanel'e geri dönülürse MenuBar görünür yapılır.
            if(previousPanel.equalsIgnoreCase("searching")){
                panelManager.setMenuBarVisible(true);
            }
            else{
                panelManager.setMenuBarVisible(false);
            }
            panelManager.showPanelByKey(previousPanel);
        });

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(backButton);     

        topBar.add(leftPanel, BorderLayout.WEST);
        add(topBar, BorderLayout.NORTH);
    }
//-------------------------------------------End of constructor method-----------------------------
}
