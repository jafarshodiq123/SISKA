/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Components.CustomButton;
import Config.DB;
import Obat.Form;
import java.awt.GridBagConstraints;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class ObatController implements Controller {

    private ArrayList<Integer> idKategori = new ArrayList<Integer>();
    private ArrayList<String> idObat = new ArrayList<String>();
    private String idObatActive;
    int status = 1;
    private JTable table;
    private JDialog form;
    private JTable listParent;
    private ArrayList<String> satuanList = new ArrayList<>();

    public ObatController(JTable table, JDialog form, JTable listSatuan) {
        this.table = table;
        this.form = form;
        this.listParent = listSatuan;
    }

    @Override
    public void tampilData() {
        try {
            ResultSet data = DB.query("SELECT data_obat.kode_obat,data_obat.nama_obat,jumlah_obat,data_obat.satuan,nama_kategori,aturan_pakai,harga FROM `data_obat`  join data_jenis_penjualan on data_obat.kode_obat =data_jenis_penjualan.kode_obat AND data_obat.satuan =data_jenis_penjualan.satuan  order by tanggal_dibuat desc");
            DefaultTableModel tabelData = (DefaultTableModel) table.getModel();
            tabelData.setRowCount(0);
            int no = 1;
            idObat.clear();
            while (data.next()) {
                Object[] dataArray = {no, data.getString("kode_obat"), data.getString("nama_obat"), data.getString("jumlah_obat"), data.getString("satuan"), data.getString("nama_kategori"), data.getString("aturan_pakai"), data.getString("harga")};
                tabelData.addRow(dataArray);
                idObat.add(data.getString("kode_obat"));
                no++;

            }
        } catch (Exception e) {
            System.out.println("error dari method tampil data " + e.getMessage());
        }
    }

    @Override
    public void tambahData(Object[] object) {
        try {

            JComboBox satuan = (JComboBox) object[0];
            JComboBox kategori = (JComboBox) object[1];
            ResultSet dataSatuan = getSatuan();
            ResultSet dataKategori = getKategori();
            satuan.removeAll();
            kategori.removeAll();
            while (dataSatuan.next()) {
                satuan.addItem(dataSatuan.getString("nama_bentuk_sediaan"));
            }
            while (dataKategori.next()) {
                kategori.addItem(dataKategori.getString("nama_kategori"));
            }
            form.pack();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        } catch (Exception e) {
        }

    }

    @Override
    public void hapusData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cariData(String kunci) {
        try {
            String sql = "SELECT data_obat.kode_obat,data_obat.nama_obat,jumlah_obat,data_obat.satuan,nama_kategori,aturan_pakai,harga FROM `data_obat`  join data_jenis_penjualan on data_obat.kode_obat =data_jenis_penjualan.kode_obat AND data_obat.satuan =data_jenis_penjualan.satuan where data_obat.nama_obat like '%" + kunci + "%'  order by tanggal_dibuat desc";
            ResultSet data = DB.query(sql);
            DefaultTableModel tabelData = (DefaultTableModel) table.getModel();
            tabelData.setRowCount(0);
            int no = 1;
            idObat.clear();
            while (data.next()) {
                Object[] dataArray = {no, data.getString("kode_obat"), data.getString("nama_obat"), data.getString("jumlah_obat"), data.getString("satuan"), data.getString("nama_kategori"), data.getString("aturan_pakai"), data.getString("harga")};
                tabelData.addRow(dataArray);
                idObat.add(data.getString("kode_obat"));
                no++;

            }
        } catch (Exception e) {
            System.out.println("error dari method tampil data " + e.getMessage());
        }
    }

    @Override
    public void simpanData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void editData(Object[] id, JPanel Field) {

    }

    @Override
    public void editData(Object[] id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addList(Object[] component) {
        try {

            JComboBox satuan = (JComboBox) component[0];
            String satuanText = satuan.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) listParent.getModel();
            if (satuanList.size() == 5) {
                JOptionPane.showMessageDialog(form, "Multi Satuan Maksimal 5");
                return;
            }
            satuanList.add(satuanText);

            model.addRow(new Object[]{satuanText, "test"});

            ResultSet dataSatuan = getSatuan();
            satuan.removeAllItems();
            while (dataSatuan.next()) {
                satuan.addItem(dataSatuan.getString("nama_bentuk_sediaan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getSatuan() {
        try {
            String sql;
            if (satuanList.size() == 0) {
                sql = "SELECT * FROM bentuk_sediaan_obat ";

            } else {
                sql = "SELECT * FROM bentuk_sediaan_obat where nama_bentuk_sediaan not in (";
                int index = 0;
                for (String satuan : satuanList) {
                    sql += "'" + satuan + "'" + (index < satuanList.size() - 1 ? "," : "");
                    index++;
                }
                sql += ")";
            }
            System.out.println(sql);
            return DB.query(sql);

        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet getKategori() {
        try {
            return DB.query("SELECT * FROM kategori ");

        } catch (Exception e) {
            return null;
        }
    }

}
