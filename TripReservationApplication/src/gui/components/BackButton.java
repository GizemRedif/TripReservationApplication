
package gui.components;

import gui.UserPanelManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import tripreservationapplication.MainFrame;


public class BackButton extends JPanel{
    
    public BackButton(String previousPanel){
        
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        
        //--------------Üst panel: Geri ve Filtrele butonları---------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(19, 29, 79));

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> {
            UserPanelManager panelManager = (UserPanelManager) MainFrame.getInstance().getContentPane();
            panelManager.setMenuBarVisible(true);
            panelManager.showPanelByKey(previousPanel);
        });

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(backButton);
        
        
        if("searching".equals(previousPanel)){
            JButton filterButton = new JButton("🔍 Filter");
            filterButton.addActionListener(e -> showFilterPopup());
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setOpaque(false);
            rightPanel.add(filterButton);
            topBar.add(rightPanel, BorderLayout.EAST);
        }
        

        

        topBar.add(leftPanel, BorderLayout.WEST);
        
        add(topBar, BorderLayout.NORTH);
        
        
    }
    
    
    
        //Filtreleme pop-up'ı acılacak ve istenilen filtreler yapılacak.
    private void showFilterPopup() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Filter Trips", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Buraya filtre seçenekleri gelecek..."));

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
