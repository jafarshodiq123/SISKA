/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author Muhammad Nor Kholit
 */
import Config.DB;
import Helper.Currency;
import Helper.FormatTanggal;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LaporanStokController implements Controller {

    private int idEdit;
    //status 1 untuk tambah 2 untuk edit
    private int status = 1;
    private JTable table;
    private JDialog form;
    private ArrayList<Object[]> penjualanList = new ArrayList<>();

    public LaporanStokController(JTable table, JDialog form) {
        this.table = table;
        this.form = form;
    }

    @Override
    public void tampilData() {
        try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_stok   order by tanggal_transaksi desc");
            int no = 1;
            // menggunakan DefaultTableModel supaya bisa menambahkan data
            DefaultTableModel tables = (DefaultTableModel) table.getModel();
            tables.fireTableDataChanged();
            tables.setRowCount(0);
            int[] arrayId = new int[10];
            penjualanList.clear();

            while (data.next()) {
                //  menyimpan data dalam bentuk array
                System.out.println(data.getString("tanggal_transaksi"));

                Object[] dataTable = {
                    no,
                    data.getString("kode_obat"),
                    data.getString("nama_obat"),
                    data.getString("obat_terjual"),
                    FormatTanggal.formatDate(data.getDate("tanggal_transaksi"))

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

    public void filterPeriode(Object[] component) {
        try {

            int index = ((JComboBox) component[0]).getSelectedIndex();

            String sql = "";
            if (index == 0) {
                sql = "SELECT obat_terjual as total_obat_terjual ,nama_obat, DATE(tanggal_transaksi) as tanggal, kode_obat FROM laporan_stok   order by tanggal_transaksi desc";
            } else if (index == 1) {
                sql = "SELECT SUM(obat_terjual) AS total_obat_terjual, nama_obat, DATE(tanggal_transaksi) as tanggal, kode_obat FROM laporan_stok GROUP BY DATE(tanggal_transaksi),kode_obat, nama_obat order  by tanggal desc; ";
            } else if (index == 2) {
                sql = "SELECT SUM(obat_terjual) AS total_obat_terjual, nama_obat, DATE_FORMAT(tanggal_transaksi, '%Y-%m') AS tanggal, kode_obat FROM laporan_stok  GROUP BY tanggal, kode_obat, nama_obat order by tanggal desc;";
            } else if (index == 3) {
                sql = "SELECT SUM(obat_terjual) AS total_obat_terjual, nama_obat, DATE_FORMAT(tanggal_transaksi, '%Y') AS tanggal, kode_obat FROM laporan_stok  GROUP BY tanggal, kode_obat, nama_obat order by tanggal desc;";
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
                System.out.println(data.getString("tanggal"));

                Object[] dataTable = {
                    no,
                    data.getString("kode_obat"),
                    data.getString("nama_obat"),
                    data.getString("total_obat_terjual"),
                    FormatTanggal.formatTanggal(data.getString("tanggal"))

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
