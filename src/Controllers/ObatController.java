/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Components.ButtonIcon;
import Components.CustomButton;
import Config.DB;
import Obat.Form;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    private JTextField namaObat;
    private JTextField aturanPakai;
    private JComboBox kategori;
    private JPanel satuanListCom;
    private ArrayList<JComboBox> satuanList = new ArrayList<JComboBox>();
    private ArrayList<JTextField> satuanTotalList = new ArrayList<JTextField>();

    public ObatController(JTable table, JDialog form, Object[] component) {
        this.table = table;
        this.form = form;
        this.namaObat = (JTextField) component[0];
        this.kategori = (JComboBox) component[1];
        this.aturanPakai = (JTextField) component[2];
        this.satuanListCom = (JPanel) component[3];
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

            ResultSet kategoriData = DB.query("SELECT * from kategori");
            addSatuan(null);
            kategori.removeAllItems();
            while (kategoriData.next()) {
                kategori.addItem(kategoriData.getString("nama_kategori"));
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
//            if (satuanList.size() == 5) {
//                JOptionPane.showMessageDialog(form, "Multi Satuan Maksimal 5");
//                return;
//            }
//            satuanList.add(satuanText);

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
//            if (satuanList.size() == 0) {
            sql = "SELECT * FROM bentuk_sediaan_obat ";
//
//            } else {
//                sql = "SELECT * FROM bentuk_sediaan_obat where nama_bentuk_sediaan not in (";
//                int index = 0;
//                for (String satuan : satuanList) {
//                    sql += "'" + satuan + "'" + (index < satuanList.size() - 1 ? "," : "");
//                    index++;
//                }
//                sql += ")";
//            }

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

    public void addSatuan(String idSelect) {
        ResultSet dataObat = getSatuan();

        int w = satuanListCom.getBounds().width;
        JPanel panel = new JPanel();
        JTextField field = new JTextField();
        JLabel label = new JLabel();
        JComboBox combo = new JComboBox();
        ButtonIcon button = new ButtonIcon();
        button.setHorizontal(true);
        button.setIcon("Assets/svg/deleteIcon.svg");

        panel.add(combo);
        if (satuanList.size() > 0) {
            panel.add(field);
            panel.add(label);
            label.setText("/" + satuanList.get(0).getSelectedItem().toString());
            label.setPreferredSize(new Dimension(70, 42));
            combo.setPreferredSize(new Dimension(350, 42));
            field.setPreferredSize(new Dimension(70, 42));
            satuanList.get(0).addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    label.setText("/ " + satuanList.get(0).getSelectedItem().toString());
                }
            });

        } else {
            combo.setPreferredSize(new Dimension(512, 42));

        }
        panel.add(button);

        panel.setBackground(Color.white);
        button.setBackground(new Color(215, 9, 83));
        panel.setMaximumSize(new Dimension(w, 50));
        button.setPreferredSize(new Dimension(42, 42));

        try {
            while (dataObat.next()) {
                combo.addItem(dataObat.getString("nama_bentuk_sediaan"));
                if (idSelect != null && idSelect.equals(dataObat.getString("id").toString())) {
                    combo.setSelectedItem(dataObat.getString("nama_bentuk_sediaan"));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        satuanTotalList.add(field);
        satuanListCom.add(panel);
        satuanList.add(combo);
        satuanListCom.repaint();
        satuanListCom.revalidate();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (satuanList.size() == 1) {
                    return;
                }
                deleteSatuanList(panel, combo);
            }

        });
    }

    public void deleteSatuanList(JPanel panel, JComboBox combo) {
        satuanList.remove(combo);
        satuanListCom.remove(panel);
        satuanListCom.repaint();
        satuanListCom.revalidate();

        System.out.println("dihapus");
    }

}
