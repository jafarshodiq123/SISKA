/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import Auth.login;
import Config.DB;
import Helper.FormatTanggal;
import Kategori.KategoriView;
import Laporan.LaporanMain;
import Laporan.PembelianView;
import Obat.ObatView;
import Obat.SatuanView;
import Pengeluaran.PengeluaranView;
import Suplier.SuplierView;
import Transaksi.PenjualanView;
import User.UserView;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.json.JSONArray;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class Main extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private String role = "";

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Hitung ukuran JFrame
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        setSize(width,height);
        main.setOpaque(true);
        cardLayout = new CardLayout();
        main.setLayout(cardLayout);
//        main.add(new ObatView(), "Medicine");
        main.add(new ObatView(), "obat");
        main.add(new SatuanView(), "satuan");
        main.add(new KategoriView(), "Category");
        main.add(new UserView(), "User");
        main.add(new SuplierView(), "Supplier");
        main.add(new LaporanMain(), "Report");
        main.add(new PenjualanView(), "penjualan");
        main.add(new Transaksi.PembelianView(), "pembelian");
        main.add(new PengeluaranView(), "pengeluaran");
//        main.add(new PembelianView(), "pembelian");
        main.add(new DashboardView(), "Home");
        pageName.setText("Dashboard");
        cardLayout.show(main, "Home");
        setExtendedState((Main.MAXIMIZED_BOTH));
        setLocationRelativeTo(null);
        tanggal.setText(FormatTanggal.formatDate(java.sql.Date.valueOf(LocalDate.now())));

        Preferences userPreferences = Preferences.userNodeForPackage(login.class);

        try {
            String datalogin = userPreferences.get("localLogin", null);

            if (datalogin != null) {

                JSONArray retrievedArray = new JSONArray(datalogin);
                role = retrievedArray.getString(3);
                username.setText("<html><div style='text-align: right;'>" + retrievedArray.getString(1) + "<br><small style='font-size:10px'>" + retrievedArray.getString(3) + "</small></div></html>");

            } else {
                new login().setVisible(true);
            }
        } catch (Exception e) {
        }

        sidebar();

//        role.setText("sda");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialog = new javax.swing.JDialog();
        dialog2 = new javax.swing.JPopupMenu();
        jPanel4 = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        menu_bar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pageName = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();

        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setBackground(new java.awt.Color(58, 98, 215));
        dialog.setBounds(new java.awt.Rectangle(100, 82, 300, 300));
        dialog.setUndecorated(true);
        dialog.setType(java.awt.Window.Type.POPUP);

        dialog2.setBorder(null);
        dialog2.setBorderPainted(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel4.setForeground(new java.awt.Color(245, 246, 250));

        main.setLayout(new javax.swing.BoxLayout(main, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(58, 98, 215));

        menu_bar.setBackground(new java.awt.Color(58, 98, 215));

        javax.swing.GroupLayout menu_barLayout = new javax.swing.GroupLayout(menu_bar);
        menu_bar.setLayout(menu_barLayout);
        menu_barLayout.setHorizontalGroup(
            menu_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );
        menu_barLayout.setVerticalGroup(
            menu_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new FlatSVGIcon("Assets/svg/closeIcon.svg"));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        username.setIcon(new FlatSVGIcon("Assets/svg/personIcon.svg"));
        username.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        username.setText("Username");
        username.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(menu_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(menu_bar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pageName.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        pageName.setForeground(new java.awt.Color(58, 98, 215));
        pageName.setText("Dashboard");

        tanggal.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        tanggal.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(pageName, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(341, 341, 341)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageName)
                    .addComponent(tanggal))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, kosong, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, kosong, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, kosong, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, kosong, ex);
//        }
//        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Table.selectionBackground", new Color(55, 98, 216));
            UIManager.put("Table.setSelectionForeground", new Color(255, 255, 255));
            UIManager.put("Table.rowHeight", 30); // Adjust the value to your desired height
            UIManager.put("Table.font", new Font("Poppins", Font.PLAIN, 13)); // Adjust the value to your desired height
            UIManager.put("Label.font", new Font("Poppins", Font.PLAIN, 12));
            UIManager.put("TextField.font", new Font("Poppins", Font.PLAIN, 12));
            UIManager.put("Button.font", new Font("Poppins", Font.PLAIN, 12));
            UIManager.put("TextArea.font", new Font("Poppins", Font.PLAIN, 12));
            UIManager.put("LookAndFeel.defaultFontName", new Font("Poppins", Font.PLAIN, 12));
            UIManager.getDefaults().put("ScrollPane.border", BorderFactory.createEmptyBorder());
            UIManager.getDefaults().put("Table.border", BorderFactory.createEmptyBorder());
            UIManager.put("TabbedPane.selectedBackground", Color.white);
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("Component.arrowType", "triangle");

        } catch (Exception e) {
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog dialog;
    private javax.swing.JPopupMenu dialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel main;
    private javax.swing.JPanel menu_bar;
    private javax.swing.JLabel pageName;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables

    private Object[][] childObat = {{"obat", "Data Obat"}, {"satuan", "Data Satuan Obat"}};
    private Object[][] childTransaksi = {{"penjualan", "Transaksi Penjualan"}, {"pembelian", "Transaksi Pembelian"}};
    private Object[][] childTransaksi2 = {{"penjualan", "Transaksi Penjualan"}};
    private Object[][] kosong = {};
//    private 
    private Object[][] menu_list_data_owner = {
        {"icf_home.svg", "icn_home.svg", "Home", "Dashboard", kosong},
        {"icf_obat.svg", "icn_obat.svg", "Medicine", "Data Obat", childObat},
        {"icf_supplier.svg", "icn_supplier.svg", "Supplier", "Data Suplier", kosong},
        {"icf_kategori.svg", "icn_kategori.svg", "Category", "Data Categori", kosong},
        {"icf_trx.svg", "icn_trx.svg", "Transaction", "Transaksi", childTransaksi},
        {"icf_report.svg", "icn_report.svg", "Report", "Laporan", kosong},
        {"icf_user.svg", "icn_user.svg", "User", "Data User", kosong},
        {"icf_user.svg", "icn_user.svg", "pengeluaran", "Data Pengeluaran", kosong}};

    private Object[][] menu_list_data_kasir = {
        {"icf_home.svg", "icn_home.svg", "Home", "Dashboard", kosong},
        {"icf_obat.svg", "icn_obat.svg", "Medicine", "Data Obat", childObat},
        {"icf_trx.svg", "icn_trx.svg", "Transaction", "Transaksi", childTransaksi2},};
    private ArrayList<JPanel> addedLabels = new ArrayList<>();

    public void sidebar() {
        menu_bar.setLayout(new GridBagLayout());
        Object[][] menu = role.equalsIgnoreCase("owner") ? menu_list_data_owner : menu_list_data_kasir;
        for (Object[] objects : menu) {
//            javax.swing.JPanel panelChild = new javax.swing.JPanel();
            javax.swing.JPanel panel = new javax.swing.JPanel();
            javax.swing.JLabel label = new javax.swing.JLabel(objects[2].toString());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            label.setForeground(Color.WHITE);
            panel.setOpaque(false);
            panel.setBackground(Color.LIGHT_GRAY);
            panel.setLayout(new GridBagLayout());
            label.setIcon(new FlatSVGIcon("Assets/ic_header/" + objects[0].toString()));
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.CENTER);

            panel.addMouseListener(new java.awt.event.MouseAdapter() {
                // Store the previously added labels for removal

                @Override
                public void mouseClicked(MouseEvent evt) {
                    Object[] childRows = (Object[]) objects[4];
                    if (childRows.length > 0) {
                        return;
                    }
                    resetStyle();
                    setBoldFont(label);
                    setBottomBorder(panel);
                    cardLayout.show(main, objects[2].toString());
                    pageName.setText(objects[3].toString());
                }

//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    super.mouseEntered(e);
//                    Point position = panel.getLocationOnScreen();
//                    dialog.setVisible(false);
//                    for (JPanel panel : addedLabels) {
//                        dialog.remove(panel);
//                    }
//                    addedLabels.clear();
//                    dialog.setVisible(false);
//                    addedLabels.clear();
//
//                    Object[] childRows = (Object[]) objects[4];
//                    javax.swing.JPanel panel2 = new javax.swing.JPanel();
//                    panel2.setBackground(new Color(51, 85, 188));
//                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
////                    panel2.setSize(200, 100);
//                    panel2.setBorder(new EmptyBorder(5, 0, 0, 0));
//                    for (Object object : childRows) {
//                        Object[] innerRow = (Object[]) object;
//                        javax.swing.JLabel label2 = new javax.swing.JLabel(innerRow[0].toString());
//                        javax.swing.JPanel panel3 = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT));
//                        label2.setHorizontalAlignment(SwingConstants.LEFT);
//                        label2.setForeground(Color.white);
//                        label2.setFont(new Font("Poppins", Font.PLAIN, 13));
//                        panel3.setBorder(new EmptyBorder(0, 10, 5, 10));
//                        panel3.setBackground(new Color(51, 85, 188));
//                        panel3.add(label2);
//                        panel2.add(panel3);
//
//                        dialog.add(panel2);
//                        addedLabels.add(panel2);
//                        panel3.addMouseListener(new java.awt.event.MouseAdapter() {
//                            @Override
//                            public void mouseClicked(MouseEvent e) {
//                                resetStyle();
//                                setBoldFont(label);
//                                setBottomBorder(panel);
//                                cardLayout.show(main, innerRow[0].toString());
//                                pageName.setText(innerRow[1].toString());
//                            }
//                        });
//
//                    }
//                    if (childRows.length > 0) {
//                        dialog.setBounds(position.x, position.y + 73, 200, childRows.length * 43);
//                        dialog.setVisible(true);
//                    }
//
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    super.mouseExited(e);
//
//                    dialog.addMouseMotionListener(new java.awt.event.MouseAdapter() {
//                        @Override
//                        public void mouseMoved(MouseEvent e) {
////                            // Check if the cursor is over the text area
//                            Point point = e.getPoint();
//                            if (panel.getBounds().contains(point) || dialog.getBounds().contains(point) || jPanel2.getBounds().contains(point)) {
//                                System.out.println("Cursor over the text area");
//                            } else {
//                                System.out.println("Cursor outside the text area");
//                                // Close the dialog when the cursor is outside the text area or the dialog
//                                dialog.dispose();
//                            }
//                        }
//                    });
////               
//
//                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    for (JPanel panel : addedLabels) {
                        dialog2.remove(panel);
                    }
                    addedLabels.clear();
                    Object[] childRows = (Object[]) objects[4];
                    javax.swing.JPanel panel2 = new javax.swing.JPanel();
                    panel2.setBackground(new Color(51, 85, 188));
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
                    panel2.setPreferredSize(new Dimension(200, 43 * childRows.length));
//                    panel2.setSize(200, 100);
                    panel2.setBorder(new EmptyBorder(5, 0, 0, 0));
                    for (Object object : childRows) {
                        Object[] innerRow = (Object[]) object;
                        javax.swing.JLabel label2 = new javax.swing.JLabel(innerRow[0].toString());
                        javax.swing.JPanel panel3 = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT));
                        label2.setHorizontalAlignment(SwingConstants.LEFT);
                        label2.setForeground(Color.white);
                        label2.setFont(new Font("Poppins", Font.PLAIN, 13));
                        panel3.setBorder(new EmptyBorder(0, 10, 5, 10));
                        panel3.setBackground(new Color(51, 85, 188));
                        panel3.add(label2);
                        panel2.add(panel3);

                        dialog2.add(panel2);
                        addedLabels.add(panel2);
                        panel3.addMouseListener(new java.awt.event.MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                resetStyle();
                                setBoldFont(label);
                                setBottomBorder(panel);
                                cardLayout.show(main, innerRow[0].toString());
                                pageName.setText(innerRow[1].toString());
                                dialog2.setVisible(false);
                            }
                        });

                    }
                    dialog2.show(panel, 0, panel.getHeight() + 5);
                }

            });
            panel.add(label);

            menu_bar.add(panel);

        }
    }

    private void resetStyle() {

        // Reset border and font style for all panels
        for (Component component : menu_bar.getComponents()) {
            if (component instanceof JPanel) {
                ((JPanel) component).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                for (Component innerComponent : ((JPanel) component).getComponents()) {
                    if (innerComponent instanceof JLabel) {
                        Font originalFont = ((JLabel) innerComponent).getFont();
                        ((JLabel) innerComponent).setFont(originalFont.deriveFont(Font.PLAIN));
                    }
                }
            }
        }
    }

    private void setBoldFont(JLabel label) {
        // Set font style to bold for the specified label
        label.setFont(label.getFont().deriveFont(Font.BOLD));
    }

    private void setBottomBorder(JPanel panel) {
        // Set bottom border for the specified panel
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(panel.getBorder(), bottomBorder));
    }

}
