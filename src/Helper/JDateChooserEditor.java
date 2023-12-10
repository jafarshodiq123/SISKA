package Helper;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import de.wannawork.jcalendar.JCalendarComboBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDateChooserEditor extends DefaultCellEditor {

    private JTable table;

    public JDateChooserEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        date = new JCalendarComboBox();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date.setDateFormat(dateFormat);

    }

    JCalendarComboBox date;

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//        if (value != null) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyykkmmss");
////           date = simpleDateFormat.format(new Date());
////            date.setDate((new Date()) value);
//        }
//        date = new JCalendarComboBox();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date.setDateFormat(dateFormat);
//                System.out.println(date);
        return date;
//        System.out.println(value);

//        return date;
    }

    public Object getCellEditorValue() {
        String formatdate = "dd-MM-yyyy";
        DateFormat foormat = new SimpleDateFormat(formatdate);
        return String.valueOf(foormat.format(date.getDate()));
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
    // Menangani perpindahan fokus ke kolom berikutnya saat memilih tanggal

//    public boolean shouldSelectCell(EventObject anEvent) {
//        // Memeriksa jika event yang terjadi adalah MouseEvent dan JDateChooser sudah memilih tanggal
//        if (anEvent instanceof MouseEvent && date.getDate() != null) {
//            int row = table.getEditingRow(); // Mendapatkan baris yang sedang diedit
//            int column = table.getEditingColumn(); // Mendapatkan kolom yang sedang diedit
//
//            // Memeriksa jika ini bukan kolom terakhir, jika tidak, pindah ke kolom berikutnya
//            if (column < table.getColumnCount() - 1) {
//                table.editCellAt(row, column + 1);
//                return false; // Mengembalikan false untuk mencegah pemrosesan lebih lanjut
//            }
//        }
//        return super.shouldSelectCell(anEvent);
//    }
}
