package gui.adminPanels;

import gui.UserPanelManager;
import gui.EditUserPanel;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;

//Adminin duzenlemek istedigi User'ın e-mailini aratması icin kullandıgı panel
public class SelectUserForEditPanel extends JPanel {

    private JTextField emailField;
    UserService userService = new UserService();

    public SelectUserForEditPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Ortada email girme alanı ve buton
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(239, 228, 210));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JLabel emailLabel = new JLabel("Enter email of user to edit:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));

        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setBackground(new Color(19, 29, 79));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        searchButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            
            //Mail alanı dolduruldu mu kontrolu yapılır.
            if (!email.isEmpty()) {              
                User searchingUser = userService.getUserByEmail(email);
                
                //Eger yazılan maile ait kullanıcı yoksa hata mesajı verir.
                if(searchingUser == null){
                    JOptionPane.showMessageDialog(this, "User not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.addPanel("editUser", new EditUserPanel(searchingUser, "selectUserForEditPanel", () -> {
                        //Edit tamamlandıktan sonra AdminPanel'e donulur.
                        upm.showPanelByKey("admin");
                    }));
                    upm.showPanelByKey("editUser");
                }
            } 
            else {
                JOptionPane.showMessageDialog(this, "Please enter an email.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        centerPanel.add(emailLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(emailField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(searchButton);

        add(centerPanel, BorderLayout.CENTER);
    }
}
