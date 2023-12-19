/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Config.DB;
import Helper.Currency;
import Helper.FormatTanggal;
import de.wannawork.jcalendar.JCalendarComboBox;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class LaporanShiftController implements Controller{

  JTable jTable1;
    @Override
    public void tampilData() {
         try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_pembelian  order by tanggal_transaksi desc");
            int no = 1;
            // menggunakan DefaultTableModel supaya bisa menambahkan data
            DefaultTableModel tables = (DefaultTableModel) jTable1.getModel();
            tables.fireTableDataChanged();
            tables.setRowCount(0);
            int[] arrayId = new int[10];
//            penjualanList.clear();

            while (data.next()) {
                //  menyimpan data dalam bentuk array

                Object[] dataTable = {
                    no,
                    data.getString("kode_transaksi"),
                    data.getString("nama_suplier"),
                    data.getString("jumlah_obat"),
                    Currency.format(data.getInt("total_harga")),
                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))
                };

                //  memasukkan data kepada tabel
                tables.addRow(dataTable);
//                penjualanList.add(new Object[]{data.getString("kode_transaksi"),
//                    data.getString("nama_suplier"),
//                    data.getString("jumlah_obat"),
//                    Currency.format(data.getInt("total_harga")),
//                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))});
                no++;
            }

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    @Override
    public void tambahData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void simpanData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editData(Object[] id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
