/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Config.DB;
import Helper.Currency;
import Helper.FormatTanggal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rexy
 */
public class LaporanPendapatanController implements Controller{


    private JTable table;
    private JDialog form;
    private ArrayList<Object[]> penjualanList = new ArrayList<>();

    public LaporanPendapatanController(JTable table, JDialog form) {
        this.table = table;
        this.form = form;
    }

    @Override
    public void tampilData() {
        try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_pendapatan order by tanggal desc");
            int no = 1;
            // menggunakan DefaultTableModel supaya bisa menambahkan data
            DefaultTableModel tables = (DefaultTableModel) table.getModel();
            tables.fireTableDataChanged();
            tables.setRowCount(0);
            penjualanList.clear();

            while (data.next()) {
                //  menyimpan data dalam bentuk array

                Object[] dataTable = {
                    no,
                    data.getString("pendapatan"),
                    data.getString("tanggal"),
                };
                //  memasukkan data kepada tabel
                tables.addRow(dataTable);

                no++;
            }

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    @Override
    public void tambahData(Object[] object) {
        form.pack();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void hapusData(Object[] object) {

    }

    public void cariData(String kunci) {
        try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_penjualan ");
            int no = 1;
            // menggunakan DefaultTableModel supaya bisa menambahkan data
            DefaultTableModel tables = (DefaultTableModel) table.getModel();
            tables.fireTableDataChanged();
            tables.setRowCount(0);
            int[] arrayId = new int[10];
            penjualanList.clear();
            while (data.next()) {
                //  menyimpan data dalam bentuk array
                Object[] dataTable = {
                    no,
                    data.getString("kode_transaksi"),
                    data.getString("nama_pengguna"),
                    data.getString("total_obat"),
                    Currency.format(data.getInt("total_harga")),
                    data.getString("tanggal_transaksi")

                };
                //  memasukkan data kepada tabel
                tables.addRow(dataTable);
                penjualanList.add(new Object[]{data.getString("kode_transaksi"),
                    data.getString("total_obat"),
                    data.getString("total_harga"),
                    data.getDate("tanggal_transaksi"),
                    data.getTimestamp("nama_pengguna")});
                no++;
            }
        } catch (Exception e) {
            System.out.println("error dari method tampil data " + e.getMessage());
        }
    }

    @Override
    public void simpanData(Object[] object) {

    }

    public void editData(Object[] rowTable) {

    }

    @Override
    public void updateData(Object[] object) {
    }

    public void filterPeriode(int index,LocalDate ranges,LocalDate rangef) {
        try {
            String sql = "";
            switch (index) {
                case 0 -> sql = "SELECT * FROM laporan_pendapatan order by tanggal desc";
                case 1 ->{
                    sql = "SELECT * FROM laporan_pendapatan where tanggal BETWEEN '"+ranges+"' AND '"+rangef+"' order by tanggal desc";
                }
                case 2 -> sql = "SELECT sum(pendapatan) AS pendapatan, DATE_FORMAT(tanggal, '%Y-%m') AS tanggal FROM laporan_pendapatan GROUP BY month(tanggal),year(tanggal) order by month(tanggal),year(tanggal) desc;";
                case 3 -> sql = "SELECT sum(pendapatan) AS pendapatan, DATE_FORMAT(tanggal, '%Y') AS tanggal FROM laporan_pendapatan GROUP BY year(tanggal) order by year(tanggal) desc;";
                default -> {
                }
            }

            ResultSet data = DB.query(sql);
            int no = 1;
            // menggunakan DefaultTableModel supaya bisa menambahkan data
            DefaultTableModel tables = (DefaultTableModel) table.getModel();
            tables.fireTableDataChanged();
            tables.setRowCount(0);
            int[] arrayId = new int[10];
            penjualanList.clear();

            while (data.next()) {
                //  menyimpan data dalam bentuk array
                

               Object[] dataTable = {
                    no,
                    data.getString("pendapatan"),
                    data.getString("tanggal"),
                };
                //  memasukkan data kepada tabel
                tables.addRow(dataTable);

                no++;
            }
        } catch (Exception e) {
            System.out.println("eror" + e.getMessage());
        }
    }
    
}
