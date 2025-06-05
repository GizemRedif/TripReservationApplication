package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import user.model.User;


public class UserMainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private User currentUser; // sınıf değişkeni

// public UserMainPanel(User currentUser) BU ŞEKİLDE CONSTRUCTOR PARAMETRESİ VEREBİLİRSİN KULLANICI TESPİTİ İÇİN
//    SONRA İÇİNE         this.currentUser = currentUser; YAZ
    public UserMainPanel() {
        setLayout(new BorderLayout());

        // 🔹 Özelleştirilmiş Menü Bar ekleniyor
        JMenuBar menuBar = createCustomMenuBar();
        add(menuBar, BorderLayout.NORTH);

        // 🔹 Content panel (CardLayout)
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new SearchTripPanel(), "searching");
        // contentPanel.add(new PastTripsPanel(), "past");
        contentPanel.add(new AccountPanel(), "account");
        
        
//        ADMİN PANELİ DÜZENLEYEBİLMEK İÇİN EKLENDİ SONRA SİL ADMİN Mİ USER Mİ DİYE BAKARAK YAZ
        contentPanel.add(new AdminPanel(), "admin");


        add(contentPanel, BorderLayout.CENTER);

        // Açılışta arama paneli gösterilsin
//        if(currentUser instanceof User){
            cardLayout.show(contentPanel, "admin");
//        }
        
    }

    private JMenuBar createCustomMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(400, 40)); // Menü bar yüksekliği büyütüldü
        menuBar.setBackground(new Color(19, 29, 79));

        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);
        menu.setOpaque(true);
        menu.setBackground(new Color(19, 29, 79));

        JMenuItem searchTripItem = new JMenuItem("Search Trips");
        JMenuItem pastTripsItem = new JMenuItem("MyPast Trips");
        JMenuItem accountItem = new JMenuItem("My Account");
        
        // Arka plan ve yazı rengi ayarları
        Color bgColor = new Color(19, 29, 79);
        Color fgColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 14);

        JMenuItem[] items = {searchTripItem, pastTripsItem, accountItem};
        for (JMenuItem item : items) {
            item.setBackground(bgColor);
            item.setForeground(fgColor);
            item.setFont(font);
        }

//        if (currentUser instanceof Admin) {
//            JMenuItem adminPanelItem = new JMenuItem("Admin Panel");
//            adminPanelItem.setBackground(bgColor);
//            adminPanelItem.setForeground(fgColor);
//            adminPanelItem.setFont(font);
//
//            adminPanelItem.addActionListener(e -> {
//                // Burada Admin panelini açabilirsin
//                // Örnek olarak:
//
//                contentPanel.add(new AdminMainPanel(), "admin");
//                cardLayout.show(contentPanel, "admin");
//            });
//
//            menu.add(adminPanelItem);
//        }
        
        menu.add(searchTripItem);
        menu.add(pastTripsItem);
        menu.add(accountItem);
        menuBar.add(menu);

        // 🔹 Menü olayları (contentPanel'e erişebilmesi için anonymous değil, metod üzerinden bağlanır)
        searchTripItem.addActionListener(e -> cardLayout.show(contentPanel, "searching"));
        pastTripsItem.addActionListener(e -> cardLayout.show(contentPanel, "past"));
        accountItem.addActionListener(e -> cardLayout.show(contentPanel, "account"));

        return menuBar;
    }
}
