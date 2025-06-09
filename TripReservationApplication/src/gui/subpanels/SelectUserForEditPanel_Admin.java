package gui.subpanels;

import gui.UserPanelManager;
import gui.components.BackButton;
import user.model.User;

import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;

public class SelectUserForEditPanel_Admin extends JPanel {

    private JTextField emailField;
    private JButton searchButton;
    UserService userService = new UserService();

    public SelectUserForEditPanel_Admin() {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 210));

        // Üstte geri butonları
        add(new BackButton("editUser"), BorderLayout.NORTH);

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

        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setBackground(new Color(19, 29, 79));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        centerPanel.add(emailLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(emailField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(searchButton);

        add(centerPanel, BorderLayout.CENTER);

        // searchButton action (isteğe göre burada bir şey yapılabilir)
        searchButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (!email.isEmpty()) {
                // Burada email ile kullanıcı aranabilir
                
                User user = userService.getUserByEmail(email);
                if(user == null){
                    JOptionPane.showMessageDialog(this, "User not found.", "Warning", JOptionPane.WARNING_MESSAGE);

                }
                else{
                    UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                    upm.addPanel("editUser", new EditUserPanel(user, () -> {
                        //Runnable
                        // Sonra geri dön
                        upm.showPanelByKey("admin");
                    }));
                    upm.showPanelByKey("editUser");
                }
                
                
                
                
                // örnek: userService.getUserByEmail(email);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an email.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
