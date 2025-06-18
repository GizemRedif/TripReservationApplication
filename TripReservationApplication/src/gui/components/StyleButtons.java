package gui.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

//The class to use to create styled buttons.

public class StyleButtons {
    
    //This method is used to create a blue button.
    public static JButton createStyledBlueButton(String buttonText) {
        JButton button = new JButton(buttonText);
        
        button.setBackground(new Color(19, 29, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        return button;
    }
    
    //This method is used to create a brown button.
    public static JButton createStyledBrownButton(String buttonText) {
        JButton button = new JButton(buttonText);
        
        button.setBackground(new Color(149, 76, 46));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        return button;
    }
}
