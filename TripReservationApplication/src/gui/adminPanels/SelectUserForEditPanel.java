package gui.adminPanels;

import gui.UserPanelManager;
import gui.EditUserPanel;
import user.model.User;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import static gui.components.StyleButtons.createStyledBlueButton;

//The panel that the Admin uses to search for the e-mail of the User he wants to edit.
public class SelectUserForEditPanel extends JPanel {

    private JTextField emailField;
    UserService userService = new UserService();

    public SelectUserForEditPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Email entry field and button in the middle
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

        //Button (created with the static method in the StyleButton class)
        JButton searchButton = createStyledBlueButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            
            // Checks whether the mail field is filled.
            if (!email.isEmpty()) {              
                User searchingUser = userService.getUserByEmail(email);
                
                //If the user for the email written does not exist, it gives an error message.
                if(searchingUser == null){
                    JOptionPane.showMessageDialog(this, "User not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.addPanel("editUser", new EditUserPanel(searchingUser, "selectUserForEditPanel", () -> {
                        //After editing is completed, return to AdminPanel.
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
