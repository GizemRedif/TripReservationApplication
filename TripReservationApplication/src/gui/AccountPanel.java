package gui;

import gui.subpanels.EditUserPanel;
import javax.swing.*;
import java.awt.*;
import tripreservationapplication.MainFrame;
import user.Service.UserService;
import user.model.User;

public class AccountPanel extends JPanel {

    private String firstName;
    private String lastName;
    private String email;
    private char gender;
    private String password;
    private String phoneNumber;

    //Kullanıcı bilgilerini guncellerse hesabım kısmında yeni bilgilerin gorunebilmesi icin kullanılacaklar
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel phoneNumberLabel;
    private JLabel genderLabel;
    
    public AccountPanel(User user) {
        
        //User'ın bilgileri alınır
        firstName = user.getName();
        lastName = user.getSurname();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        gender = user.getGender();
        password = user.getPassword();
        
        UserService userService = new UserService(); //Kullanıcı bilgilerini gunellemek icin kullanılacak

        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(239, 228, 210));
        add(contentPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(350, 400));
        infoPanel.setBackground(new Color(239, 228, 210));

        
        //Name Panel---------------------------------------------------------------------------------------
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(Color.WHITE);
        nameLabel = new JLabel("Hello " + firstName + " " + lastName + "!");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        namePanel.add(nameLabel);
        infoPanel.add(namePanel);
        infoPanel.add(Box.createVerticalStrut(20));
        
        
        //Email Panel---------------------------------------------------------------------------------------
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        emailPanel.setBackground(Color.WHITE);
        emailLabel = new JLabel("Email: " + email);
        emailPanel.add(emailLabel);
        infoPanel.add(emailPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Password panel---------------------------------------------------------------------------------------
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(Color.WHITE);
        passwordLabel = new JLabel("Password: *********                                                           ");
        passwordPanel.add(passwordLabel);
        infoPanel.add(passwordPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Phone Number Panel---------------------------------------------------------------------------------------
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.setBackground(Color.WHITE);
        phoneNumberLabel = new JLabel("Phone Number: " + phoneNumber + "                                                           ");
        phoneNumberPanel.add(phoneNumberLabel);
        infoPanel.add(phoneNumberPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        
        //Gender Panel---------------------------------------------------------------------------------------
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        genderLabel = new JLabel("Gender: ");
        genderPanel.add(genderLabel);
        infoPanel.add(genderPanel);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //About US Panel---------------------------------------------------------------------------------------
        JButton aboutButton = new JButton("About Us");
        styleButton(aboutButton);
        aboutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "aabbcc", "About Us", JOptionPane.INFORMATION_MESSAGE);
        });
        infoPanel.add(aboutButton);
        infoPanel.add(Box.createVerticalStrut(20));

        
        //Change Information Panel---------------------------------------------------------------------------------------
        JButton changeInfButton = new JButton("Change Information");
        styleButton(changeInfButton);
        infoPanel.add(changeInfButton);

        //Change Inf butonuna basılınca bir pop-up cıkacak ve kullanıcı istedigini guncelleyecek.
        changeInfButton.addActionListener(e -> {
            UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();

            upm.addPanel("editUser", new EditUserPanel(user, () -> {
                //Runnable: 
                // Bu panelin label'larını güncelle
                this.updateLabels(user);

                // Sonra geri dön
                upm.showPanelByKey("account");
            }));

            upm.showPanelByKey("editUser");
        });

        
        // Logout Button Panel---------------------------------------------------------------------------------------
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> {
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.setContentPane(new LoginRegisterPanel());
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(logoutButton);

        // Delete Account Button Panel---------------------------------------------------------------------------------------
        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setFocusPainted(true);
        deleteAccountButton.setBackground(new Color(149, 76, 46));
        deleteAccountButton.setForeground(Color.WHITE);
        deleteAccountButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteAccountButton.setMaximumSize(new Dimension(200, 30));

        deleteAccountButton.addActionListener(e -> {
     
            //HESABI SİL
            
        });

        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(deleteAccountButton);


        
        contentPanel.add(infoPanel);
    }

    
    private void styleButton(JButton button) {
        button.setFocusPainted(true);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(200, 30));
    }
    
    //Bu metot, GridBagLayout içinde bir bileşeni belirli bir hücreye (satır, sütun) yerleştirmek için kullanılan GridBagConstraints nesnesini kopyalar ve konumunu (gridx, gridy) belirler.
    private GridBagConstraints gbcAt(GridBagConstraints base, int x, int y) {
        GridBagConstraints copy = (GridBagConstraints) base.clone();  //mevcut constraints ayarlarının bir kopyasını çıkarır. Böylece aynı ayarları tekrar yazmadan kullanırsın.
        copy.gridx = x;
        copy.gridy = y;
        return copy;
    }
    
    public void updateLabels(User updatedUser) {
        nameLabel.setText("Hello " + updatedUser.getName() + " " + updatedUser.getSurname() + "!");
        phoneNumberLabel.setText("Phone Number: " + updatedUser.getPhoneNumber());
        genderLabel.setText("Gender: " + updatedUser.getGender());
        passwordLabel.setText("Password: *********");
    }
}

