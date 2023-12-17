/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Transaksi;

import java.sql.ResultSet;
import Components.DeleteButtonRenderer;
import Config.DB;
import Controllers.TransaksiPenjualanController;
import com.formdev.flatlaf.ui.FlatTextBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import table.TableCustom;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class PenjualanView extends javax.swing.JPanel {

    private TransaksiPenjualanController controller;
    boolean adaQuery = false;
    Preferences userPreferences = Preferences.userNodeForPackage(PenjualanView.class);
    ArrayList<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);
    ArrayList<Boolean> cellEdit = new ArrayList<Boolean>();

    /**
     * Creates new form PenjualanView
     */
    public PenjualanView() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Hitung ukuran JFrame
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        setSize(width, height);
        controller = new TransaksiPenjualanController(table, dataObat, dataJenis, qty, stok, harga, bayar, addList, batal, totalHarga, kembalian, btnBayar, lnamaobat, lkategoriobat, laturanpakai);
        controller.tampilData();
//        AutoCompleteDecorator.decorate(dataObat);
//        revalidate();
//        validate();
//        JTextField tf = (JTextField) dataObat.getEditor().getEditorComponent();
//
// Add keylistener to JTextField!
//        JTextComponent editor = (JTextComponent) dataObat.getEditor().getEditorComponent();
//        editor.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                updateComboBox();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                updateComboBox();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                updateComboBox();
//            }
//
//            private void updateComboBox() {
//                // Mendapatkan nilai terkini dari editor
//                String input = editor.getText();
//
//                // Menyaring item yang cocok dengan nilai input
//                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) dataObat.getModel();
//                int size = model.getSize();
//                model.removeAllElements();
//                for (int i = 0; i < size; i++) {
//                    String value = model.getElementAt(i);
////                    if (value.toLowerCase().contains(input.toLowerCase())) {
////                        model.addElement(value);
////                    }
//                }
//
//                // Tampilkan drop-down list
//                dataObat.setPopupVisible(true);
//            }
//        });
//        dataObat.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Mendapatkan nilai terkini dari JComboBox
//                String selectedValue = (String) dataObat.getSelectedItem();
//                System.out.println("Nilai terkini: " + selectedValue);
//            }
//        });
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
        jPanel2 = new Components.CustomPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bayar = new Components.CustomField();
        jLabel7 = new javax.swing.JLabel();
        kembalian = new Components.CustomField();
        btnBayar = new Components.ButtonIcon();
        totalHarga = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new Components.CustomPanel();
        jLabel3 = new javax.swing.JLabel();
        dataObat = new javax.swing.JComboBox<>();
        dataJenis = new javax.swing.JComboBox<>();
        qty = new Components.CustomField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stok = new Components.CustomField();
        harga = new Components.CustomField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addList = new Components.ButtonIcon();
        batal = new Components.ButtonIcon();
        jPanel4 = new Components.CustomPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new Components.CustomPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        laturanpakai = new javax.swing.JLabel();
        lnamaobat = new javax.swing.JLabel();
        lkategoriobat = new javax.swing.JLabel();

        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(58, 98, 215));

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Detail Pembayaran");

        jLabel6.setForeground(java.awt.SystemColor.controlLtHighlight);
        jLabel6.setText("Total Pembayaran");

        bayar.setBorder(new FlatTextBorder());
        bayar.setBackground(new java.awt.Color(82, 115, 214));
        bayar.setBorder(new FlatTextBorder());
        bayar.setForeground(new java.awt.Color(255, 255, 255));
        bayar.setOpaque(true);
        bayar.setPlaceholder("");
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarKeyReleased(evt);
            }
        });

        jLabel7.setForeground(java.awt.SystemColor.controlLtHighlight);
        jLabel7.setText("Total Kembalian");

        kembalian.setBorder(new FlatTextBorder());
        kembalian.setEditable(false);
        kembalian.setBackground(new java.awt.Color(67, 106, 224));
        kembalian.setBorder(new FlatTextBorder());
        kembalian.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        kembalian.setEnabled(false);
        kembalian.setFocusable(false);
        kembalian.setOpaque(true);
        kembalian.setPlaceholder("");
        kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianActionPerformed(evt);
            }
        });

        btnBayar.setIcon("Assets/svg/saveIcon.svg");btnBayar.setHorizontal(true);
        btnBayar.setForeground(new java.awt.Color(58, 98, 215));
        btnBayar.setText("Simpan");
        btnBayar.setEnabled(false);
        btnBayar.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        totalHarga.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        totalHarga.setForeground(new java.awt.Color(255, 255, 255));
        totalHarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalHarga.setText("Rp. 0.00");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(50, 50, 50)
                        .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(58, 98, 215));
        jLabel3.setText("Informasi Penjualan");

        dataObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        dataObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataObatItemStateChanged(evt);
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
        dataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataObatActionPerformed(evt);
            }
        });

        dataJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        dataJenis.setEnabled(false);
        dataJenis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataJenisItemStateChanged(evt);
            }
        });
        dataJenis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dataJenisFocusGained(evt);
            }
        });
        dataJenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataJenisMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dataJenisMouseReleased(evt);
            }
        });

        qty.setPlaceholder("Masukkan qty di beli");
        qty.setText("0");
        qty.setEnabled(false);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        jLabel1.setText("Pilih Obat");

        jLabel2.setText("Jenis Penjualan");

        jLabel4.setText("Qty");

        qty.setPlaceholder("Masukkan qty di beli");
        stok.setText("0");
        stok.setEnabled(false);

        qty.setPlaceholder("Masukkan qty di beli");
        harga.setText("Rp.0");
        harga.setEnabled(false);

        jLabel8.setText("Stok Tersedia");

        jLabel10.setText("Harga Jual");

        addList.setIcon("Assets/svg/addIcon.svg");
        addList.setBackground(new java.awt.Color(58, 98, 215));
        addList.setHorizontal(true);
        addList.setForeground(new java.awt.Color(255, 255, 255));
        addList.setText("Tambah");
        addList.setEnabled(false);
        addList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addListActionPerformed(evt);
            }
        });

        addList.setIcon("Assets/svg/addIcon.svg");
        batal.setBackground(new java.awt.Color(215, 9, 83));
        batal.setForeground(new java.awt.Color(255, 255, 255));
        batal.setText("Batal");
        batal.setEnabled(false);
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataObat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(dataJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addList, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataObat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addList, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Satuan", "Harga Jual", "Qty", "Subtotal", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMaxWidth(100);
            table.getColumnModel().getColumn(4).setMinWidth(100);
            table.getColumnModel().getColumn(4).setMaxWidth(100);
            table.getColumnModel().getColumn(5).setMinWidth(100);
            table.getColumnModel().getColumn(5).setMaxWidth(100);
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
                .addComponent(jScrollPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(58, 98, 215));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Obat");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kategori Obat");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Aturan Pakai");

        laturanpakai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        laturanpakai.setForeground(new java.awt.Color(255, 255, 255));

        lnamaobat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lnamaobat.setForeground(new java.awt.Color(255, 255, 255));

        lkategoriobat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lkategoriobat.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lkategoriobat, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                            .addComponent(lnamaobat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(laturanpakai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12))
                        .addGap(17, 17, 17))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lnamaobat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lkategoriobat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laturanpakai, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased

    }//GEN-LAST:event_tableKeyReleased

    private void dataObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataObatItemStateChanged
        if (controller.obatIsClicked) {
            controller.setJenis();
        }
    }//GEN-LAST:event_dataObatItemStateChanged

    private void dataJenisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataJenisItemStateChanged
        if (controller.jenisIsClicked) {
            controller.setHargaStok();
            qty.setText("1");
        }
    }//GEN-LAST:event_dataJenisItemStateChanged

    private void dataObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataObatMouseClicked
        controller.obatIsClicked = true;
    }//GEN-LAST:event_dataObatMouseClicked

    private void dataJenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataJenisMouseClicked

    }//GEN-LAST:event_dataJenisMouseClicked

    private void dataJenisMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataJenisMouseReleased
    }//GEN-LAST:event_dataJenisMouseReleased

    private void dataObatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataObatMouseReleased

    }//GEN-LAST:event_dataObatMouseReleased

    private void dataJenisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dataJenisFocusGained
        controller.jenisIsClicked = true;

    }//GEN-LAST:event_dataJenisFocusGained

    private void bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyReleased
        controller.pembayaran();

    }//GEN-LAST:event_bayarKeyReleased

    private void addListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addListActionPerformed
        controller.tambahKeList();
    }//GEN-LAST:event_addListActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        controller.resetForm();
    }//GEN-LAST:event_batalActionPerformed

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
        controller.setQty();
    }//GEN-LAST:event_qtyKeyReleased

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        controller.simpanData(new Object[]{this});
    }//GEN-LAST:event_btnBayarActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        reset();
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void dataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataObatActionPerformed
//        String value = dataObat.getSelectedItem().toString().toLowerCase();
//        int totalData = dataObat.getModel().getSize();
//
//        DefaultComboBoxModel<String> originalModel = new DefaultComboBoxModel();
//        DefaultComboBoxModel<String> filteredModel = new DefaultComboBoxModel<>();
//        try {
//            ResultSet data = controller.getObatData();
//            while (data.next()) {
//                originalModel.addElement(data.getString("nama_obat"));
//            }
//        } catch (Exception e) {
//        }
//
//        for (int i = 0; i < totalData; i++) {
//            String value2 = dataObat.getItemAt(i).toString();
//
//            if (value2.contains(value)) {
//                filteredModel.addElement(value2);
//            }
//        }
//
//        controller.setJenis();
//        if (filteredModel.getSize() > 0) {
//            dataObat.setModel(filteredModel);
//            dataObat.showPopup();
//        } else {
//            dataObat.setModel(originalModel);
//        }

    }//GEN-LAST:event_dataObatActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String sqlQuery = "SELECT * FROM `printerview` where kode_transaksi = 1";
            String path = "src/iReportdata/printpenjualan.jrxml";
            JasperDesign jasperDesign = JRXmlLoader.load(path);

            // Membuat objek JRDesignQuery
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sqlQuery);

            // Mengaitkan JRDesignQuery dengan JasperDesign
            jasperDesign.setQuery(newQuery);

            // Langkah 3: Mengisi data ke laporan JasperReports
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<>();
            // Mengisi laporan dengan data dari database
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DB.getConnection());

            // Menampilkan laporan (opsional)
            JasperViewer viewer = new JasperViewer(jasperPrint);
            viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void reset() {
//        removeAll();
//        initComponents();
        controller = new TransaksiPenjualanController(table, dataObat, dataJenis, qty, stok, harga, bayar, addList, batal, totalHarga, kembalian, btnBayar, lnamaobat, lkategoriobat, laturanpakai);
        controller.tampilData();
//        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.ButtonIcon addList;
    private Components.ButtonIcon batal;
    private Components.CustomField bayar;
    private Components.ButtonIcon btnBayar;
    private javax.swing.JComboBox<String> dataJenis;
    private javax.swing.JComboBox<String> dataObat;
    private Components.CustomField harga;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.CustomField kembalian;
    private javax.swing.JLabel laturanpakai;
    private javax.swing.JLabel lkategoriobat;
    private javax.swing.JLabel lnamaobat;
    private Components.CustomField qty;
    private Components.CustomField stok;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalHarga;
    // End of variables declaration//GEN-END:variables
}
//
//class AutoComplete {
//
//    static void enable(JComboBox<String> comboBox) {
//        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
//        textField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                update();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                update();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                update();
//            }
//
//            private void update() {
//                SwingUtilities.invokeLater(() -> {
//                    String text = textField.getText().toLowerCase();
//                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
//                    for (String item : getItems(comboBox)) {
//                        if (item.toLowerCase().startsWith(text)) {
//                            model.addElement(item);
//                        }
//                    }
//                    comboBox.setModel(model);
//                    comboBox.setSelectedIndex(-1);
//                    comboBox.setPopupVisible(model.getSize() > 0);
//                });
//            }
//        });
//
//        textField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_TAB) {
//                    String selected = (String) comboBox.getSelectedItem();
//                    textField.setText(selected);
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//    }
//
//    private static List<String> getItems(JComboBox<String> comboBox) {
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < comboBox.getItemCount(); i++) {
//            items.add(comboBox.getItemAt(i));
//        }
//        return items;
//    }
//}
