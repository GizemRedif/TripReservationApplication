package gui;

import gui.components.CreateAUser;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import user.model.User; 


public class LoginRegisterPanel extends JPanel {

    UserService userService = new UserService(); //Bu sınıftaki createUser ve login metotlarını kullanabilmek icin olusturuldu
    private final JTabbedPane tabbedPane; 

    public LoginRegisterPanel() {
        setLayout(new GridBagLayout()); 
        this.setBackground(new Color(37, 77, 112)); 
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout(0, 20)); // Başlık ile sekme arası boşluk
        containerPanel.setPreferredSize(new Dimension(420, 460));
        containerPanel.setBackground(Color.WHITE);

        //Başlık paneli
        JLabel titleLabel = new JLabel("THİS TİCKET", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(19, 29, 79));  //Yazı rengi
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        //Sekmeler altta olacak şekilde oluşturuluyor
        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());

        containerPanel.add(titleLabel, BorderLayout.NORTH);
        containerPanel.add(tabbedPane, BorderLayout.CENTER);

        add(containerPanel);
    }
//-------------------------------------------End of constructor method-----------------------------
    
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(239, 228, 210));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        
        loginButton.addActionListener(e -> {
            User user = userService.login(emailField.getText().trim(), new String(passwordField.getPassword()));           
            if(user == null){
                JOptionPane.showMessageDialog(null, "Email or password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {  
                //Giris yapan User'ın, Passanger mi Admin mi oldugu kontrolu yapılır ve ona gore panel acılır. 
                MainFrame.getInstance().showUserPanelManager(user);
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // boşluk
        panel.add(loginButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        //User olusturma paneli cagırılır ve tappedPane icerisinde gosterilir,
        return new CreateAUser(userService, tabbedPane,true, "Passenger");
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(true);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
