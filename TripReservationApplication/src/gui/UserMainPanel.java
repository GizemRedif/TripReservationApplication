package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserMainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    

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
        // contentPanel.add(new AccountPanel(), "account");

        add(contentPanel, BorderLayout.CENTER);

        // Açılışta arama paneli gösterilsin
        cardLayout.show(contentPanel, "searching");
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
