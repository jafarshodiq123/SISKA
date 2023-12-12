/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Transaksi;

import java.sql.ResultSet;
import Components.DeleteButtonRenderer;
import Controllers.TransaksiPembelianController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import Helper.JDateChooserEditor;
import Helper.IntegerCellEditor;
import javax.swing.JCheckBox;
import table.TableCustom;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class PembelianView extends javax.swing.JPanel {

    private TransaksiPembelianController controller;
    boolean adaQuery = false;
    Preferences userPreferences = Preferences.userNodeForPackage(PembelianView.class);
    ArrayList<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);
    ArrayList<Boolean> cellEdit = new ArrayList<Boolean>();

    /**
     * Creates new form PenjualanView
     */
    public PembelianView() {
        initComponents();
        Object[] component = {table, dataSuplier, dataObat, qty, harga};
        controller = new Controllers.TransaksiPembelianController(component);
        controller.tampilData();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new Components.CustomPanel();
        jLabel3 = new javax.swing.JLabel();
        dataSuplier = new javax.swing.JComboBox<>();
        dataObat = new javax.swing.JComboBox<>();
        qty = new Components.CustomField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        harga = new Components.CustomField();
        jLabel10 = new javax.swing.JLabel();
        addList = new Components.ButtonIcon();
        btnBayar = new Components.ButtonIcon();
        jPanel4 = new Components.CustomPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(58, 98, 215));
        jLabel3.setText("Informasi Pembelian");

        dataSuplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        dataSuplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataSuplierItemStateChanged(evt);
            }
        });
        dataSuplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataSuplierMouseClicked(evt);
            }
        });
        dataSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSuplierActionPerformed(evt);
            }
        });

        dataObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        dataObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataObatItemStateChanged(evt);
            }
        });
        dataObat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dataObatFocusGained(evt);
            }
        });
        dataObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataObatMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dataObatMouseReleased(evt);
            }
        });

        qty.setPlaceholder("Masukkan qty di beli");
        qty.setText("0");
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        jLabel1.setText("Pilih Suplier");

        jLabel2.setText("Pilih Obat");

        jLabel4.setText("Qty");

        qty.setPlaceholder("Masukkan qty di beli");
        harga.setText("Rp.0");

        jLabel10.setText("Harga Satuan");

        addList.setIcon("Assets/svg/addIcon.svg");
        addList.setBackground(new java.awt.Color(58, 98, 215));
        addList.setHorizontal(true);
        addList.setForeground(new java.awt.Color(255, 255, 255));
        addList.setText("Tambah");
        addList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addListActionPerformed(evt);
            }
        });

        btnBayar.setIcon("Assets/svg/saveIcon.svg");btnBayar.setHorizontal(true);
        btnBayar.setBackground(new java.awt.Color(58, 98, 215));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("Simpan");
        btnBayar.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataObat, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(addList, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(3, 3, 3)
                                .addComponent(dataSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addList, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addComponent(dataObat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Satuan", "Harga Beli", "Qty", "Tanggal Kadaluarsa", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(150);
            table.getColumnModel().getColumn(0).setMaxWidth(150);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setMinWidth(150);
            table.getColumnModel().getColumn(2).setMaxWidth(150);
            table.getColumnModel().getColumn(3).setMinWidth(150);
            table.getColumnModel().getColumn(3).setMaxWidth(150);
            table.getColumnModel().getColumn(4).setMinWidth(150);
            table.getColumnModel().getColumn(4).setMaxWidth(150);
            table.getColumnModel().getColumn(5).setCellEditor(new JDateChooserEditor(new JCheckBox(),table));
            table.getColumnModel().getColumn(6).setMinWidth(80);
            table.getColumnModel().getColumn(6).setMaxWidth(80);
            table.getColumnModel().getColumn(6).setCellRenderer(new DeleteButtonRenderer());
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased

    }//GEN-LAST:event_tableKeyReleased

    private void dataObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataObatItemStateChanged

    }//GEN-LAST:event_dataObatItemStateChanged

    private void dataObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataObatMouseClicked

    }//GEN-LAST:event_dataObatMouseClicked

    private void dataObatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataObatMouseReleased
    }//GEN-LAST:event_dataObatMouseReleased

    private void dataObatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dataObatFocusGained

    }//GEN-LAST:event_dataObatFocusGained

    private void addListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addListActionPerformed
           controller.tambahKeTable();
    }//GEN-LAST:event_addListActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int columnClicked = table.columnAtPoint(evt.getPoint());

        // Kolom ke-6 adalah kolom dengan index 5
        if (columnClicked == 6) {
            controller.resetTable();
        } else {
            controller.editData(new Object[]{});
        }
    }//GEN-LAST:event_tableMouseClicked

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
    }//GEN-LAST:event_qtyKeyReleased

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        controller.simpanData(new Object[]{});
    }//GEN-LAST:event_btnBayarActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        reset();
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void dataSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSuplierActionPerformed

    }//GEN-LAST:event_dataSuplierActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        Object[] component = {table, dataSuplier, dataObat, qty, harga};
        controller = new Controllers.TransaksiPembelianController(component);
        controller.tampilData();
        controller.spIsClick = false;
    }//GEN-LAST:event_formAncestorAdded

    private void dataSuplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataSuplierItemStateChanged
        if (controller.spIsClick) {

            try {
                controller.setTable();
            } catch (SQLException ex) {
                Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dataSuplierItemStateChanged

    private void dataSuplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSuplierMouseClicked
        controller.spIsClick = true;
    }//GEN-LAST:event_dataSuplierMouseClicked

    public void reset() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.ButtonIcon addList;
    private Components.ButtonIcon btnBayar;
    private javax.swing.JComboBox<String> dataObat;
    private javax.swing.JComboBox<String> dataSuplier;
    private Components.CustomField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.CustomField qty;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

class AutoComplete {

    static void enable(JComboBox<String> comboBox) {
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                SwingUtilities.invokeLater(() -> {
                    String text = textField.getText().toLowerCase();
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                    for (String item : getItems(comboBox)) {
                        if (item.toLowerCase().startsWith(text)) {
                            model.addElement(item);
                        }
                    }
                    comboBox.setModel(model);
                    comboBox.setSelectedIndex(-1);
                    comboBox.setPopupVisible(model.getSize() > 0);
                });
            }
        });

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    String selected = (String) comboBox.getSelectedItem();
                    textField.setText(selected);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private static List<String> getItems(JComboBox<String> comboBox) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            items.add(comboBox.getItemAt(i));
        }
        return items;
    }
}
