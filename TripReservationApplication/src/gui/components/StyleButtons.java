package gui.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

//Stili olan butonları oluşturmak için kullanılacak sınıf.

public class StyleButtons {
    
    //Mavi renkli buton oluşturmak için bu metot kullanılır.
    public static JButton createStyledBlueButton(String buttonText) {
        JButton button = new JButton(buttonText);
        
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        return button;
    }
    
    //Kahverengi renkli buton oluşturmak için bu metot kullanılır. 
    public static JButton createStyledBrownButton(String buttonText) {
        JButton button = new JButton(buttonText);
        
        button.setBackground(new Color(149, 76, 46));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        return button;
    }
}
