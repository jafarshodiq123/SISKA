/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class CustomButton extends JButton {

    public CustomButton() {
        setText("");
        setBorder(null);
        setBackground(new Color(58,98,215));
        setForeground(Color.white);
        
    }

    public void setPlaceholder(String text) {
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, text);
    }

    public void setIcon(String path) {
        super.setIcon(new FlatSVGIcon(path));
    }
}
