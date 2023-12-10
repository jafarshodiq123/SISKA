package Components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonRenderer extends Components.ButtonIcon implements TableCellRenderer {

    private Runnable onDelete;

    public DeleteButtonRenderer(Runnable onDelete) {
        this.onDelete = onDelete;

        // Set properties for the button
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(Color.RED);
        setFocusPainted(false);
        setBorderPainted(false);
        setIcon("Assets/svg/deleteIcon.svg");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("dsadas");
                // Handle button click event here
                onDelete.run(); // Run the provided method
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Return the button as the renderer component
        return this;
    }
}
