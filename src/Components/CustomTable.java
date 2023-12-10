package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class CustomTable extends JTable {

    private static final int HEADER_ROW_HEIGHT = 30; // Set your desired header row height

    public CustomTable() {
        super();
        JTableHeader header = getTableHeader();
//        header.setDefaultRenderer(new HeaderRenderer());
        header.setPreferredSize(new Dimension(40, 40)); // Set the header row height
    }

  

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component returnComp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(248, 248, 251);
        Color whiteColor = new Color(255, 255, 255);
//        setIntercellSpacing(new Dimension(20, 20));
        
        if (!returnComp.getBackground().equals(getSelectionBackground())) {
            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
            returnComp.setBackground(bg);
        }

        setShowHorizontalLines(true);
        return returnComp;
    }
}
