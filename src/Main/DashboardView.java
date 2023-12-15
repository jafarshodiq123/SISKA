/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;

import Auth.login;
import Helper.Currency;
import Helper.konversiintbln;
import chart.ModelChart;
import java.util.prefs.Preferences;
import javax.swing.JLabel;
import Config.DB;
import Helper.FormatTanggal;
import java.sql.ResultSet;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author AkbarMr.
 */
public class DashboardView extends javax.swing.JPanel {

    /**
     * Creates new form DashboardView
     */
    Preferences userPreferences = Preferences.userNodeForPackage(DashboardView.class);
    private Object statement;

    public DashboardView() {

        Preferences userPreferences = Preferences.userNodeForPackage(login.class);
        System.out.println(userPreferences.get("localLogin", ""));

        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Hitung ukuran JFrame
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        setSize(width, height);
        chart();
        ntf_stok();
        ntf_exp();
        items();
        pendapatan1hari();
        pengeluaran1hari();
//        jLabel2.setText(userPreferences.get("Username", null));
    }

    public void reset() {
        removeAll();
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Hitung ukuran JFrame
        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        setSize(width, height);
        chart();
        ntf_stok();
        ntf_exp();
        items();
        pendapatan1hari();
        pengeluaran1hari();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notif_panel = new Components.CustomPanel();
        notif_exp = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        item_panel = new Components.CustomPanel();
        item_panel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        total_items = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chart_panel = new javax.swing.JPanel();
        lineChart = new chart.LineChart();
        chartTitle = new javax.swing.JLabel();
        pendapatanperhari = new Components.CustomPanel();
        pendapatan_harini = new javax.swing.JLabel();
        pendapatanLabel = new javax.swing.JLabel();
        pengeluaranperhari = new Components.CustomPanel();
        pengeluaran_harini = new javax.swing.JLabel();
        pengeluaran = new javax.swing.JLabel();
        jPanel2 = new Components.CustomPanel();
        notif_stok = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1221, 406));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        //notif_panel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        notif_exp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setToolTipText("Notifikasi Obat Kadaluarsa");
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 98, 215));
        jLabel1.setText("Daftar obat kadaluarsa / akan kadaluarsa  ");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jPanel1.add(jLabel1);

        notif_exp.setViewportView(jPanel1);

        javax.swing.GroupLayout notif_panelLayout = new javax.swing.GroupLayout(notif_panel);
        notif_panel.setLayout(notif_panelLayout);
        notif_panelLayout.setHorizontalGroup(
            notif_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notif_exp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        notif_panelLayout.setVerticalGroup(
            notif_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notif_exp, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        item_panel.setBackground(new java.awt.Color(58, 98, 215));
        item_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 5));

        total_items.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        total_items.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Obat");

        javax.swing.GroupLayout item_panelLayout = new javax.swing.GroupLayout(item_panel);
        item_panel.setLayout(item_panelLayout);
        item_panelLayout.setHorizontalGroup(
            item_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(item_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(total_items, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        item_panelLayout.setVerticalGroup(
            item_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item_panelLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(total_items, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(15, 15, 15))
        );

        chart_panel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        chart_panel.setBackground(new java.awt.Color(255, 255, 255));

        chartTitle.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        chartTitle.setText("Laporan Tahun ");

        javax.swing.GroupLayout chart_panelLayout = new javax.swing.GroupLayout(chart_panel);
        chart_panel.setLayout(chart_panelLayout);
        chart_panelLayout.setHorizontalGroup(
            chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chart_panelLayout.createSequentialGroup()
                .addGroup(chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chart_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE))
                    .addGroup(chart_panelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(chartTitle)))
                .addContainerGap())
        );
        chart_panelLayout.setVerticalGroup(
            chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chart_panelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(chartTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        pendapatanperhari.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        pendapatanperhari.setBackground(new java.awt.Color(109, 207, 102));
        pendapatanperhari.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 5));

        pendapatan_harini.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        pendapatan_harini.setForeground(new java.awt.Color(255, 255, 255));

        pendapatanLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        pendapatanLabel.setForeground(new java.awt.Color(255, 255, 255));
        pendapatanLabel.setText("Pendapatan Hari Ini");

        javax.swing.GroupLayout pendapatanperhariLayout = new javax.swing.GroupLayout(pendapatanperhari);
        pendapatanperhari.setLayout(pendapatanperhariLayout);
        pendapatanperhariLayout.setHorizontalGroup(
            pendapatanperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendapatanperhariLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pendapatanperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pendapatanLabel)
                    .addComponent(pendapatan_harini, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pendapatanperhariLayout.setVerticalGroup(
            pendapatanperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendapatanperhariLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(pendapatan_harini, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pendapatanLabel)
                .addGap(17, 17, 17))
        );

        pengeluaranperhari.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        pengeluaranperhari.setBackground(new java.awt.Color(215, 9, 83));
        pengeluaranperhari.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 5));

        pengeluaran_harini.setBackground(new java.awt.Color(102, 184, 207));
        pengeluaran_harini.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        pengeluaran_harini.setForeground(new java.awt.Color(255, 255, 255));

        pengeluaran.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        pengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        pengeluaran.setText("Pengeluaran Hari ini");

        javax.swing.GroupLayout pengeluaranperhariLayout = new javax.swing.GroupLayout(pengeluaranperhari);
        pengeluaranperhari.setLayout(pengeluaranperhariLayout);
        pengeluaranperhariLayout.setHorizontalGroup(
            pengeluaranperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengeluaranperhariLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pengeluaranperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pengeluaran)
                    .addComponent(pengeluaran_harini, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pengeluaranperhariLayout.setVerticalGroup(
            pengeluaranperhariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengeluaranperhariLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pengeluaran_harini, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pengeluaran)
                .addGap(15, 15, 15))
        );

        notif_stok.setHorizontalScrollBar(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel3.setToolTipText("Notifiksai Stok Obat");
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 98, 215));
        jLabel2.setText("Daftar stok habis / akan habis");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jPanel3.add(jLabel2);

        notif_stok.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(notif_stok))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(notif_stok)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(item_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pendapatanperhari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pengeluaranperhari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(chart_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notif_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendapatanperhari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pengeluaranperhari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chart_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(notif_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        reset();
    }//GEN-LAST:event_formAncestorAdded
    private void chart() {
        try {
            List<ModelChart> dataPengeluaran = new ArrayList<>();
            java.sql.Connection connection = DB.getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet data = statement.executeQuery("SELECT bulan, SUM(total_pengeluaran) AS total_pengeluaran, SUM(total_penjualan) AS total_penjualan FROM ( SELECT MONTH(tanggal_pengeluaran) AS bulan, SUM(total_pengeluaran) AS total_pengeluaran, NULL AS total_penjualan FROM pengeluaran GROUP BY bulan UNION ALL SELECT MONTH(tanggal_transaksi) AS bulan, NULL AS total_pengeluaran, SUM(total_harga) AS total_penjualan FROM transaksi_penjualan GROUP BY bulan ) AS combined_data GROUP BY bulan;");
            ResultSet data = statement.executeQuery("SELECT rugi_bersih,pendapatan,pengeluaran,laba_bersih,RIGHT(bulan_tahun, 2) as bulan FROM `laporan`");
//            ResultSet data = DB.query("SELECT * FROM datachart");
            for (int bulan = 1; bulan <= 12; bulan++) {
                String namaBulan = konversiintbln.getMonth(bulan);
                int pengeluaran = 0;
                int penjualan = 0;
                int laba_bersih = 0;


                // Cari data yang cocok untuk bulan saat ini dalam ResultSet
                while (data.next()) {
                    int bulanData = data.getInt("bulan");
                    if (bulanData == bulan) {
                        String total_pengeluaran = data.getString("pengeluaran");
                        String total_Penjualan = data.getString("pendapatan");
                        String total_lababersih = data.getString("laba_bersih");


                        // Cek dan atur nilai ke 0 jika null
//                        if (total_pengeluaran == null) {
//                            total_pengeluaran = "0";
//                        }
//                        if (total_Penjualan == null) {
//                            total_Penjualan = "0";
//                        }

                        // Tambahkan ke total untuk bulan saat ini
                        pengeluaran += Integer.parseInt(total_pengeluaran);
                        penjualan += Integer.parseInt(total_Penjualan);
                        laba_bersih += Integer.parseInt(total_lababersih);
                    }
                }

                // Buat objek ModelChart dan tambahkan ke dalam list
                ModelChart s = new ModelChart(namaBulan, new double[]{penjualan, pengeluaran,laba_bersih});
                dataPengeluaran.add(s);

                // Reset pointer ResultSet ke awal setiap kali loop selesai memproses satu bulan
                data.beforeFirst();
            }

// Tambahkan data ke dalam lineChart
            lineChart.addLegend("Pendapatan", Color.blue, Color.blue);
            lineChart.addLegend("Pengeluaran", Color.MAGENTA, Color.MAGENTA);
            lineChart.addLegend("Laba Bersih", Color.ORANGE, Color.ORANGE);

            for (ModelChart d : dataPengeluaran) {
                lineChart.addData(d);
            }
//            lineChart.addData(new ModelChart("Januari", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("Februari", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("March", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("April", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("May", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("June", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("July", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("August", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("September", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("October", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("November", new double[]{0, 0}));
//            lineChart.addData(new ModelChart("December", new double[]{0, 0}));
            lineChart.start();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ntf_stok() {
        ResultSet data = DB.query("SELECT obat.nama_obat, obat.jumlah_obat AS jumlah_obat\n"
                + "FROM obat\n"
                + "JOIN stok_obat ON obat.kode_obat = stok_obat.kode_obat\n"
                + "JOIN (\n"
                + "    SELECT kode_obat, MAX(id) AS max_id\n"
                + "    FROM detail_penjualan\n"
                + "    GROUP BY kode_obat\n"
                + ") latest_detail ON obat.kode_obat = latest_detail.kode_obat\n"
                + "JOIN detail_penjualan ON latest_detail.kode_obat = detail_penjualan.kode_obat\n"
                + "                  AND latest_detail.max_id = detail_penjualan.id\n"
                + "JOIN transaksi_penjualan ON detail_penjualan.kode_transaksi = transaksi_penjualan.kode_transaksi\n"
                + "WHERE obat.jumlah_obat <= 10\n"
                + "GROUP BY obat.kode_obat, obat.nama_obat\n"
                + "ORDER BY transaksi_penjualan.tanggal_transaksi DESC;");
        String pesan = "";
        try {
            while (data.next()) {
                String namaObat = data.getString("nama_obat");
                int jumlahObat = data.getInt("jumlah_obat");
                System.out.println(jumlahObat);
                if (jumlahObat == 0) {
                    pesan = "<html>Stok Obat " + namaObat + " Telah Habis</html>";
                } else {
                    pesan = "<html>Stok Obat " + namaObat + " Akan Segera Habis Tersisa " + jumlahObat + "</html>";
                }
                JLabel LABEL = new JLabel(pesan);
                LABEL.setMaximumSize(new Dimension(300, 40));
                LABEL.setFont(new Font("Poppins medium", Font.PLAIN, 14));
                LABEL.setBorder(new EmptyBorder(20, 5, 20, 5));
                jPanel3.add(LABEL);
            }
        } catch (Exception e) {
            System.err.println("query error: " + e.getMessage());
        }
    }

    private void ntf_exp() {
//        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        int yPos = 20;
        LocalDate date = LocalDate.now();
        ResultSet data = DB.query("SELECT nama_obat, stok_obat.jumlah_obat,satuan, tanggal_kadaluarsa, CASE WHEN tanggal_kadaluarsa <= CURRENT_DATE THEN 1 ELSE 2 END AS status FROM stok_obat JOIN data_obat ON data_obat.kode_obat = stok_obat.kode_obat WHERE stok_obat.jumlah_obat > 0 AND( tanggal_kadaluarsa <= CURRENT_DATE OR tanggal_kadaluarsa > CURRENT_DATE )AND tanggal_kadaluarsa < CURRENT_DATE + INTERVAL 7 DAY ORDER BY tanggal_kadaluarsa DESC limit 10; ");
        String pesan = "";
        try {
            while (data.next()) {
                String namaObat = data.getString("nama_obat");
                String jumlahObat = data.getString("jumlah_obat");
//                pesan = "Obat " + namaObat + " telah kadaluarsa sebanyak " + jumlahObat;
                LocalDate tanggalKadaluarsa = data.getDate("tanggal_kadaluarsa").toLocalDate();
                long selisihHari = ChronoUnit.DAYS.between(tanggalKadaluarsa, LocalDate.now());
//                pesan = "<html>Obat " + namaObat + " telah kadaluarsa sebanyak " + jumlahObat + "</html>";
                pesan = "<html><p>" + jumlahObat + "" + " " + data.getString("satuan") + "  obat " + namaObat + " telah kadaluarsa  </p> </html>";
                if (data.getInt("status") == 2) {
                    pesan = "<html><p>" + jumlahObat + "" + " " + data.getString("satuan") + "  obat " + namaObat + "  akan segera kadaluarsa   </p></html>";

                }
                JPanel jp = new JPanel();
                jp.setBackground(Color.white);
//                jp.setMaximumSize(new Dimension(288, 70));
                jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
                jp.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel label = new JLabel(pesan);
                label.setMaximumSize(new Dimension(300, 40));
                System.out.println(jPanel1.getBounds().getWidth());;
                label.setFont(new Font("Poppins semibold", Font.PLAIN, 14));
                jp.add(label);

//                label.setBounds(0, yPos, 300, 30); // Atur koordinat dan ukuran label
//                jPanel1.add(label);
                String pesantgl;
                if (selisihHari != 0) {
                    pesantgl = "Tgl Exp : " + FormatTanggal.formatDate(java.sql.Date.valueOf(tanggalKadaluarsa));
//                    +selisihHari + " Yang Lalu";
                } else {
                    pesantgl = "hari ini";
                }

                JLabel labelSelisihHari = new JLabel(pesantgl);
                labelSelisihHari.setFont(new Font("Poppins", Font.PLAIN, 12));

                jp.add(labelSelisihHari);
                jPanel1.add(jp);
                // Tinggi setiap label + margin
                //                yPos += 40;
            }
        } catch (Exception e) {
            System.err.println("query error: " + e.getMessage());
        }
    }

    private void items() {
        //Total Item
        ResultSet data = DB.query("SELECT COUNT(*) as total FROM obat WHERE jumlah_obat != 0");
        String pesan = "";
        try {
            if (data.next()) {
                // Memeriksa apakah ada hasil dari query
                int total = data.getInt("total");
                pesan = total + " item";
            } else {
                // Jika tidak ada data yang ditemukan, menampilkan "0 items"
                pesan = "0 item";
            }
            total_items.setText(pesan);
        } catch (Exception e) {
            System.err.println("query error: " + e.getMessage());
        }
    }

    private void pendapatan1hari() {
        //Pendapatan Per Hari
        LocalDate date = LocalDate.now();
        ResultSet data = DB.query("SELECT SUM(total_harga) as total FROM transaksi_penjualan WHERE DATE(tanggal_transaksi) = '" + date + "'");
        String pesan = "";
        try {
            if (data.next()) {
                // Memeriksa apakah ada hasil dari query
                int total = data.getInt("total");
                pendapatan_harini.setText(Currency.format(total));
            } else {
                // Jika tidak ada data yang ditemukan, menampilkan "0 items"
                pendapatan_harini.setText("Rp.0");
            }
            Date currentDate = new Date();

            // Membuat format tanggal
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Memformat tanggal
            String formattedDate = dateFormat.format(currentDate);

            pendapatanLabel.setText("Pendapatan  hari ini");

        } catch (Exception e) {
            System.err.println("query error: " + e.getMessage());
        }
    }

    private void pengeluaran1hari() {
        //Pendapatan Per Hari
        LocalDate date = LocalDate.now();
        int monthNow = date.getMonthValue();
        ResultSet data = DB.query("SELECT SUM(total_pengeluaran) as total FROM pengeluaran WHERE MONTH(tanggal_pengeluaran) = '" + monthNow + "'");
        String pesan = "";
        String[] monthName = {"Januari", "Februari",
            "Maret", "April", "Mei", "Juni", "Juli",
            "Agustus", "September", "Oktober", "November",
            "Desember"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        try {
            if (data.next()) {
                // Memeriksa apakah ada hasil dari query
                int total = data.getInt("total");
                pengeluaran_harini.setText(Currency.format(total));
                pengeluaran.setText("Pengeluaran bulan " + month);
            } else {
                // Jika tidak ada data yang ditemukan, menampilkan "0 items"
                pengeluaran_harini.setText("Rp.0");
            }

        } catch (Exception e) {
            System.err.println("query error: " + e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chartTitle;
    private javax.swing.JPanel chart_panel;
    private javax.swing.JPanel item_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private chart.LineChart lineChart;
    private javax.swing.JScrollPane notif_exp;
    private javax.swing.JPanel notif_panel;
    private javax.swing.JScrollPane notif_stok;
    private javax.swing.JLabel pendapatanLabel;
    private javax.swing.JLabel pendapatan_harini;
    private javax.swing.JPanel pendapatanperhari;
    private javax.swing.JLabel pengeluaran;
    private javax.swing.JLabel pengeluaran_harini;
    private javax.swing.JPanel pengeluaranperhari;
    private javax.swing.JLabel total_items;
    // End of variables declaration//GEN-END:variables
}
