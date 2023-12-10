/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class CustomPanel extends JPanel{

    public CustomPanel() {
        putClientProperty(FlatClientProperties.STYLE, "arc:30");
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

    }
    
}
