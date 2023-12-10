/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class CustomTable extends JTable {

    /**
     *
     * @param renderer
     * @param row
     * @param column
     * @return
     */
    ;

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component returnComp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(241, 241, 241);
        Color whiteColor = Color.WHITE;
        getTableHeader().setBackground(new Color(33, 66, 163));
        getTableHeader().setForeground(Color.WHITE);
        if (!returnComp.getBackground().equals(getSelectionBackground())) {
            Color bg;
            bg = (row % 2 == 0 ? alternateColor : whiteColor);
            returnComp.setBackground(bg);
            bg = null;
        }
        Dimension padding = new Dimension(1, 1);
        setIntercellSpacing(padding);
        setGridColor(new Color(204,204,204));
// Set the intercell spacing to 0 pixels for selected cells
//        setIntercellSpacing(new Dimension(0, 0));
//        setShowGrid(true);
//        setShowVerticalLines(true);
//        setShowHorizontalLines(true);
        return returnComp;
    }
}
