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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class CustomField extends JTextField {

    public CustomField() {
        setText("");
        putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
    }

    public void setPlaceholder(String text) {
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, text);
    }

    public void setIcon(String path) {
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(path));
    }
}
