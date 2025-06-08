package gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import searchCriteria.TripSearchCriteria;
import trip.model.Trip;
import java.util.List;
import trip.repository.TripRepository;
import trip.service.TripService;
import user.model.User;

public class SearchTripPanel extends JPanel {
    
    TripService tripService = new TripService(); //Tripleri filtrelemek icin kullanılacak. Kriterler search butona tıklanınca olusturulacak.
    
    String[] cities = {
    "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
    "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
    "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
    "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"
};

    

    public SearchTripPanel(User user) {
        
        setLayout(new GridBagLayout()); // Panel ortalanıyor

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(500, 300)); // TabbedPane büyütüldü

        tabbedPane.addTab("Bus 🚌", createSearchForm("Bus",tabbedPane, user ));
        tabbedPane.addTab("Flight ✈️", createSearchForm("Flight", tabbedPane, user));

        add(tabbedPane); // Ortalanmış şekilde eklendi
    }
     
    private JPanel createSearchForm(String vehicle, JTabbedPane tabbedPane, User user) {
        
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 220)); // Küçük ve kompakt yapı

        if("Bus".equals(vehicle)){  //Bus ve Flight arka planı farklı olması icin 
        panel.setBackground(new Color(239, 228, 210));
        outerPanel.setBackground(new Color(239, 228, 210));
        }
        else {
        panel.setBackground(Color.WHITE);
        outerPanel.setBackground(Color.WHITE);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10); // Bileşenin etrafındaki boşluk (üst, sol, alt, sağ)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Yatay olarak genişlesin ama dikeyde sabit kalsın
      
        // Kalkış yeri
        JLabel fromLabel = new JLabel("From:");
        JComboBox<String> fromCombo = new JComboBox<>(cities); 
        
        // Varış yeri
        JLabel toLabel = new JLabel("To:");
        JComboBox<String> toCombo = new JComboBox<>(cities);

        // Tarih
        JLabel dateLabel = new JLabel("Date:");
        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> dayCombo = new JComboBox<>();

        setupDateComboBoxes(yearCombo, monthCombo, dayCombo);


        // Tarih altında yazan tarih format etiketi yazısı
        JLabel dateFormatLabel = new JLabel("Format: YYYY-MM-DD");
        dateFormatLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        dateFormatLabel.setForeground(Color.GRAY);
        
        // Arama butonu
        JButton searchButton = new JButton("Search");
        styleButton(searchButton);        
        searchButton.addActionListener(e -> {
            TripSearchCriteria tripCriteria = new TripSearchCriteria();

            //Kullanıcının liste ile sectigi tarih LocalDateTime turune donusturuluyor
            int year = Integer.parseInt((String) yearCombo.getSelectedItem());
            int month = Integer.parseInt((String) monthCombo.getSelectedItem());
            int day = Integer.parseInt((String) dayCombo.getSelectedItem());
            LocalDateTime departureTime = LocalDate.of(year, month, day).atStartOfDay();
    
            //TripSearchCriteria nesnesine kullanıcının girdigi degerler tanıyor. 
            tripCriteria.setDepartureStation((String)fromCombo.getSelectedItem());
            tripCriteria.setArrivalStation((String) toCombo.getSelectedItem());
            if (vehicle.equals("Bus")) {
                tripCriteria.setTripType(trip.model.BusTrip.class);
            } 
            else if (vehicle.equals("Flight")) {
                tripCriteria.setTripType(trip.model.FlightTrip.class);
            }            
            tripCriteria.setDepartureTime(departureTime);
            
            
            //Uygun tripler listeye alınır ve listelenirken hata kontrolu yapılır.
            List<Trip> tripsToList = new ArrayList<>();
            try {
                tripsToList = tripService.filterTrips(tripCriteria);
            } 
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Geçersiz Arama Kriteri", JOptionPane.ERROR_MESSAGE);
                return; // hata varsa devam etme
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Beklenmeyen bir hata oluştu:\n" + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Listelenen trip bos mu kontrolu yapılır.
            if (tripsToList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No trips were found that match your criteria. Please try another search.", "No results", JOptionPane.INFORMATION_MESSAGE);
            } 
            else{
            //uygun tripleri gosteren TripsPanel'e gecis yapılır.
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);  //SearchTripPanel uzerindeki JFrame bulunur. Bu, arayüzde içeriği değiştirmek için gerekli.
            frame.setContentPane(new TripsPanel(tripsToList, user));  //TripsPanel, JFrame’in ana içeriği (content pane) olarak ayarlanıyor.
            frame.revalidate();  //Arayuzu yeniden çizmek / güncellemek için kullanılır.
            }
        });


        int row = 0;
        Insets defaultInsets = new Insets(10, 10, 5, 10);
        Insets buttonInsets = new Insets(15, 10, 10, 10);

        //Altta olusturulan addToPanel metoduyla elemanlar panele ekleniyor.
        // from
        addToPanel(panel, gbc, fromLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, fromCombo, 1, row, 1, defaultInsets);

        // to
        row++;
        addToPanel(panel, gbc, toLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, toCombo, 1, row, 1, defaultInsets);

        // date
        row++;
        addToPanel(panel, gbc, dateLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, yearCombo, 1, row, 1, defaultInsets);
        addToPanel(panel, gbc, monthCombo, 2, row, 1, defaultInsets);
        addToPanel(panel, gbc, dayCombo, 3, row, 1, defaultInsets);

        // format info
        row++;
        addToPanel(panel, gbc, dateFormatLabel, 1, row, 3, defaultInsets);

        // button
        row++;
        addToPanel(panel, gbc, searchButton, 0, row, 3, buttonInsets);

        // dış panel
        outerPanel.add(panel, BorderLayout.CENTER);
        return outerPanel;
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
    
    //GridBagConstraints ayarlarını tekrar tekrar yazmaktan kurtarır.
    private void addToPanel(JPanel panel, GridBagConstraints gbc, Component comp, int x, int y, int width, Insets insets) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.insets = insets;
        panel.add(comp, gbc);
    }

    //Date olarak bugunden onceki tarihlerin listelenmesi engellenir ayrıca her yıla ve aya ozel var olan gunler listelenir.
    private void setupDateComboBoxes(JComboBox<String> yearCombo, JComboBox<String> monthCombo, JComboBox<String> dayCombo) {
    LocalDate today = LocalDate.now();
    int currentYear = today.getYear();
    int currentMonth = today.getMonthValue();
    int currentDay = today.getDayOfMonth();

    // YIL: sadece bugünden itibaren
    for (int y = currentYear; y <= 2030; y++) {
        yearCombo.addItem(String.valueOf(y));
    }

    // AY: sadece bu yıldan itibaren
    for (int m = currentMonth; m <= 12; m++) {
        monthCombo.addItem(String.format("%02d", m));
    }

    // GÜN: bugünden itibaren
    int maxDay = YearMonth.of(currentYear, currentMonth).lengthOfMonth();
    for (int d = currentDay; d <= maxDay; d++) {
        dayCombo.addItem(String.format("%02d", d));
    }

    // YIL dinleyicisi
    yearCombo.addActionListener(e -> {
        int selectedYear = Integer.parseInt((String) yearCombo.getSelectedItem());
        monthCombo.removeAllItems();
        int startMonth = (selectedYear == currentYear) ? currentMonth : 1;

        for (int m = startMonth; m <= 12; m++) {
            monthCombo.addItem(String.format("%02d", m));
        }

        if (monthCombo.getItemCount() > 0) {
            monthCombo.setSelectedIndex(0);
            monthCombo.dispatchEvent(new java.awt.event.ActionEvent(monthCombo, 0, ""));
        }
    });

    // AY dinleyicisi
    monthCombo.addActionListener(e -> {
        if (monthCombo.getItemCount() == 0) return;

        int selectedYear = Integer.parseInt((String) yearCombo.getSelectedItem());
        int selectedMonth = Integer.parseInt((String) monthCombo.getSelectedItem());

        dayCombo.removeAllItems();
        int startDay = (selectedYear == currentYear && selectedMonth == currentMonth) ? currentDay : 1;
        int max = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();

        for (int d = startDay; d <= max; d++) {
            dayCombo.addItem(String.format("%02d", d));
        }
    });
}

}
