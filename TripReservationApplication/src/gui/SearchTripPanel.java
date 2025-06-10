package gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import dto.TripSearchCriteria;
import trip.model.Trip;
import java.util.List;
import trip.service.TripService;
import tripreservationapplication.MainFrame;
import user.model.Admin;
import user.model.User;

public class SearchTripPanel extends JPanel {
    
    TripService tripService = new TripService(); //Tripleri filtrelemek icin kullanılacak. Kriterler search butona tıklanınca olusturulacak.
    
    String[] cities = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
    "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
    "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
    "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

    
    public SearchTripPanel(User user) {
        setLayout(new GridBagLayout()); // Panel ortalanıyor

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(500, 300)); 

        tabbedPane.addTab("Bus 🚌", createSearchForm("Bus", user));
        tabbedPane.addTab("Flight ✈️", createSearchForm("Flight", user));

        add(tabbedPane); 
    }
    //-------------------------------------------End of constructor method-----------------------------

     
    private JPanel createSearchForm(String vehicle, User user) {
        
        JPanel outerPanel = new JPanel(new BorderLayout()); 
        JPanel panel = new JPanel(new GridBagLayout()); 
        panel.setPreferredSize(new Dimension(400, 220));

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
        setupDateComboBoxes(yearCombo, monthCombo, dayCombo, user);

        // Tarih formatı yazısı
        JLabel dateFormatLabel = new JLabel("Format: YYYY-MM-DD");
        dateFormatLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        dateFormatLabel.setForeground(Color.GRAY);
        
        // Arama butonu---------------------------------------------------------------------------------
        JButton searchButton = new JButton("Search");
        styleButton(searchButton);        
        searchButton.addActionListener(e -> {
            
            TripSearchCriteria tripCriteria = new TripSearchCriteria();

            //Kullanıcının combo ile sectigi tarih LocalDateTime turune donusturuluyor
            int year = Integer.parseInt((String) yearCombo.getSelectedItem());
            int month = Integer.parseInt((String) monthCombo.getSelectedItem());
            int day = Integer.parseInt((String) dayCombo.getSelectedItem());
            LocalDateTime departureTime = LocalDate.of(year, month, day).atStartOfDay();
    
            //TripSearchCriteria nesnesine, kullanıcının girdigi degerler tanıyor. 
            tripCriteria.setDepartureStation((String)fromCombo.getSelectedItem());
            tripCriteria.setArrivalStation((String) toCombo.getSelectedItem());
            if (vehicle.equals("Bus")) {
                tripCriteria.setTripType(trip.model.BusTrip.class);
            } 
            else if (vehicle.equals("Flight")) {
                tripCriteria.setTripType(trip.model.FlightTrip.class);
            }            
            tripCriteria.setDepartureDate(departureTime);
            
            
            //Uygun tripler listeye alınır ve listelenirken hata kontrolu yapılır.
            List<Trip> tripsToList = new ArrayList<>();
            try {
                tripsToList = tripService.filterTrips(tripCriteria);
            } 
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Search Criteria", JOptionPane.ERROR_MESSAGE);
                return; // hata varsa devam etme
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Listelenen trip bos mu kontrolu yapılır.
            if (tripsToList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No trips were found that match your criteria. Please try another search.", "No results", JOptionPane.INFORMATION_MESSAGE);
            } 
            else{
                UserPanelManager upm = (UserPanelManager) MainFrame.getInstance().getContentPane();
                upm.addPanel("trips", new TripsPanel(tripsToList, user));  // paneli CardLayout’a ekle
                upm.showPanelByKey("trips");                            // geçiş yap
                upm.setMenuBarVisible(false); //Menü gizlendi

            }
        });
//---------Arama Butonu sonu-------------------------------------------------------------------------------------------------------------  
        
        int row = 0;
        Insets defaultInsets = new Insets(10, 10, 5, 10);
        Insets buttonInsets = new Insets(15, 10, 10, 10);

        //Altta olusturulan addToPanel metoduyla elemanlar panele ekleniyor.
        addToPanel(panel, gbc, fromLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, fromCombo, 1, row, 1, defaultInsets);

        row++;
        addToPanel(panel, gbc, toLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, toCombo, 1, row, 1, defaultInsets);

        row++;
        addToPanel(panel, gbc, dateLabel, 0, row, 1, defaultInsets);
        addToPanel(panel, gbc, yearCombo, 1, row, 1, defaultInsets);
        addToPanel(panel, gbc, monthCombo, 2, row, 1, defaultInsets);
        addToPanel(panel, gbc, dayCombo, 3, row, 1, defaultInsets);

        row++;
        addToPanel(panel, gbc, dateFormatLabel, 1, row, 3, defaultInsets);

        row++;
        addToPanel(panel, gbc, searchButton, 0, row, 3, buttonInsets);

        outerPanel.add(panel, BorderLayout.CENTER);
        return outerPanel;
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(true);
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
    private void setupDateComboBoxes(JComboBox<String> yearCombo, JComboBox<String> monthCombo, JComboBox<String> dayCombo, User user) {
        
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();
        int maxDay = YearMonth.of(currentYear, currentMonth).lengthOfMonth();

        //Admin ve Passengera gore secilebilecek tarihler ayarlanır. 
        //Admin eski tripleri de goruntuleyebilecekken ;passenger sadece bugun ve sonrasını goruntuleyebilir.

        int startYear = (user instanceof Admin) ? 2023 : currentYear;
        for (int y = startYear; y <= 2030; y++) {
            yearCombo.addItem(String.valueOf(y));
        }

        int startMonth = (user instanceof Admin) ? 1 : currentMonth;
        for (int m = startMonth; m <= 12; m++) {
            monthCombo.addItem(String.format("%02d", m));
        }

        int startDay = (user instanceof Admin) ? 1 : currentDay;
        for (int d = startDay; d <= maxDay; d++) {
            dayCombo.addItem(String.format("%02d", d));
        }


        // YIL dinleyicisi
        yearCombo.addActionListener(e -> {
            int selectedYear = Integer.parseInt((String) yearCombo.getSelectedItem());
            monthCombo.removeAllItems();
            int startMon = (user instanceof Admin || selectedYear != currentYear) ? 1 : currentMonth;

            for (int m = startMon; m <= 12; m++) {
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
            int startDy = (selectedYear == currentYear && selectedMonth == currentMonth) ? currentDay : 1;
            int max = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();

            for (int d = startDy; d <= max; d++) {
                dayCombo.addItem(String.format("%02d", d));
            }
        });
    }
}
