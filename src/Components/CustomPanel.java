/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class CustomPanel extends JPanel {

    private int shadowSize = 10; // Adjust the shadow size as needed

    public CustomPanel() {
        putClientProperty(FlatClientProperties.STYLE, "arc:30");
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        putClientProperty(FlatClientProperties.POPUP_DROP_SHADOW_PAINTED,
                 true);

    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Create a rounded rectangle for the main content
        RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, width - shadowSize, height - shadowSize, 10, 10);
        Area area = new Area(rect);

        // Create a rounded rectangle for the shadow
        RoundRectangle2D shadow = new RoundRectangle2D.Double(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2, 10, 10);
        Area shadowArea = new Area(shadow);

        // Subtract the shadow area from the main content area
        area.subtract(shadowArea);

        // Set shadow color and draw the shadow
        g2d.setColor(new Color(0, 0, 0, 100)); // Adjust the shadow color and transparency
        g2d.fill(area);

        // Dispose of the graphics context
        g2d.dispose();

        // Call the superclass method to paint the main content
        super.paintComponent(g);
    }

}
