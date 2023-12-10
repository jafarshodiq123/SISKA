/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.Component;

public class IntegerCellEditor extends DefaultCellEditor {
    public IntegerCellEditor() {
        super(new JTextField());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JTextField textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        textField.setDocument(new IntegerDocument()); // Menggunakan kustomisasi Document

        return textField;
    }

    @Override
    public boolean stopCellEditing() {
       try {
            String value = (String) getCellEditorValue();
            if (value == null || value.trim().isEmpty()) {
                return super.stopCellEditing(); // Mengizinkan nilai kosong atau null
            }
            Integer.parseInt(value); // Mengonversi nilai sel ke integer
        } catch (NumberFormatException e) {
            return false; // Jika tidak valid, editing tidak berhenti
        }
        return super.stopCellEditing(); // Jika valid, editing berhenti
    }
}

class IntegerDocument extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
//        if (str == null) {
//            return;
//        }

        // Memastikan input yang dimasukkan adalah angka
        if (str.matches("\\d+")) {
            super.insertString(offs, str, a);
        }
    }
}
