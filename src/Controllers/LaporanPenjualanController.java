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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LaporanPenjualanController implements Controller {

    private int idEdit;
    //status 1 untuk tambah 2 untuk edit
    private int status = 1;
    private JTable table;
    private JDialog form;
    private ArrayList<Object[]> penjualanList = new ArrayList<>();

    public LaporanPenjualanController(JTable table, JDialog form) {
        this.table = table;
        this.form = form;
    }

    @Override
    public void tampilData() {
        try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_penjualan  order by tanggal_transaksi desc");
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
                    data.getString("kode_transaksi"),
                    data.getString("nama_pengguna"),
                    data.getString("total_obat"),
                    Currency.format(data.getInt("total_harga")),
                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))
                    

                };
                //  memasukkan data kepada tabel
                tables.addRow(dataTable);
                penjualanList.add(new Object[]{data.getString("kode_transaksi"),
                    data.getString("total_obat"),
                    data.getString("total_harga"),
                    data.getDate("tanggal_transaksi"),
                    data.getString("nama_pengguna")});
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
        try {
            int confirm = JOptionPane.showConfirmDialog(table, "Yakin menghapus data?");
            if (confirm != 0) {
                return;
            }
            int row = (int) object[0];
            if (row < 0) {
                JOptionPane.showMessageDialog(table, "Tidak ada baris yang dipilih");
                return;

            }
            int id = (int) penjualanList.get(row)[0];
            DB.query2("DELETE FROM kategori where id='" + id + "'");
            tampilData();

        } catch (Exception e) {
        }
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
        JTextField namaKategoriField = (JTextField) object[0];
        String namaKategori = namaKategoriField.getText();
        try {
            if (penjualanList.stream().anyMatch(satuan -> satuan[1].toString().trim().equalsIgnoreCase(namaKategori.trim()) && ((int) satuan[0]) != idEdit)) {
                JOptionPane.showMessageDialog(form, "Nama Kategori Sudah Ada");
            } else if (namaKategori.equals("")) {
                JOptionPane.showMessageDialog(form, "Nama Kategori Tidak Boleh Kosong");

            } else {
                if (status == 1) {
                    DB.query2("INSERT INTO kategori (nama_kategori)Values ('" + namaKategori + "')");
                } else {
                    DB.query2("UPDATE kategori set nama_kategori = '" + namaKategori + "' where id ='" + idEdit + "'");
                    status = 1;
                    idEdit = -1;
                }

                tampilData();
                form.dispose();
                namaKategoriField.setText("");
            }

        } catch (Exception e) {
            System.out.println("error simpan data " + e.getMessage());
        }
    }

    public void editData(Object[] rowTable) {
        int row = (int) rowTable[0];
        if (row < 0) {
            JOptionPane.showMessageDialog(table, "Tidak ada baris yang dipilih");
            return;
        }
        JTextField namaKategori = (JTextField) rowTable[1];
        String namaFromTable = table.getValueAt(row, 1).toString();
        namaKategori.setText(namaFromTable);
        status = 2;
        idEdit = (int) penjualanList.get(row)[0];
        form.pack();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void updateData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
