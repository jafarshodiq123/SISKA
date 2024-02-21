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
import Laporan.PembelianView;
import de.wannawork.jcalendar.JCalendarComboBox;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LaporanPembelianController implements Controller {

    private int idEdit;
    //status 1 untuk tambah 2 untuk edit
    private int status = 1;
    private JTable table;
    private JDialog form;
    private JLabel txtkodetransaksi;
    private JLabel value_nama_sup;
    private JLabel value_stokbrg;
    private JLabel txttotalh2;
    private JLabel value_tgl_pem;
    private JTable TBLdetail_2;
    private JCalendarComboBox jdata3;
    private JCalendarComboBox jdata4;
    private ArrayList<Object[]> penjualanList = new ArrayList<>();

    public LaporanPembelianController(JTable table, JDialog form, Object[] com) {
        this.table = table;
        this.form = form;
        this.txtkodetransaksi = (JLabel) com[0];
        this.value_nama_sup = (JLabel) com[1];
        this.value_stokbrg = (JLabel) com[2];
        this.txttotalh2 = (JLabel) com[3];
        this.value_tgl_pem = (JLabel) com[4];
        this.TBLdetail_2 = (JTable) com[5];
        this.jdata3 = (JCalendarComboBox) com[6];
        this.jdata4 = (JCalendarComboBox) com[7];
    }

    @Override
    public void tampilData() {
        try {
            // mengambil data dari table kategori       
            ResultSet data = DB.query("SELECT * FROM laporan_pembelian  order by tanggal_transaksi desc");
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
                    data.getString("nama_suplier"),
                    data.getString("jumlah_obat"),
                    Currency.format(data.getInt("total_harga")),
                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))
                };

                //  memasukkan data kepada tabel
                tables.addRow(dataTable);
                penjualanList.add(new Object[]{data.getString("kode_transaksi"),
                    data.getString("nama_suplier"),
                    data.getString("jumlah_obat"),
                    Currency.format(data.getInt("total_harga")),
                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))});
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
            ResultSet data = DB.query("SELECT * FROM laporan_pembelian ");
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

    public void filter() {
        String tampilan1 = "yyyy-MM-dd";
        SimpleDateFormat tgl1 = new SimpleDateFormat(tampilan1);
        Date date1 = jdata3.getDate(); // Ganti jdata1 dengan objek Date yang dimiliki
        Date date2 = jdata4.getDate(); // Ganti jdata2 dengan objek Date yang dimiliki

        String tanggalawal = tgl1.format(date1);
        String tanggalakhir = tgl1.format(date2);

        // Konversi string tanggal ke objek LocalDate
        LocalDate awal = LocalDate.parse(tanggalawal);
        LocalDate akhir = LocalDate.parse(tanggalakhir);
        if (!awal.isBefore(akhir)) {
            LocalDate temp = awal;
            awal = akhir;
            akhir = temp;
        }
        try {
            int no = 1;
            ResultSet data = DB.query("SELECT * FROM laporan_pembelian WHERE tanggal_transaksi BETWEEN '" + awal + "' and '" + akhir + "'");
            DefaultTableModel tabel = (DefaultTableModel) table.getModel();
            tabel.setRowCount(0);
            while (data.next()) {
                Object[] dataTable = {
                    no,
                    data.getString("kode_transaksi"),
                    data.getString("nama_suplier"),
                    data.getString("jumlah_obat"),
                    Currency.format(data.getInt("total_harga")),
                    FormatTanggal.format(data.getTimestamp("tanggal_transaksi"))
                };
                tabel.addRow(dataTable);
                no++;
            }
        } catch (Exception e) {
            Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void detail() {
        try {
            int no = 1;
            String Kodetrx1 = table.getValueAt(table.getSelectedRow(), 1).toString();
            String namasup = table.getValueAt(table.getSelectedRow(), 2).toString();
            String totbrg = table.getValueAt(table.getSelectedRow(), 3).toString();
            String tottharga = table.getValueAt(table.getSelectedRow(), 4).toString();
            String tgltrpem = table.getValueAt(table.getSelectedRow(), 5).toString();
            txtkodetransaksi.setText(Kodetrx1);
            value_nama_sup.setText(namasup);
            value_stokbrg.setText(totbrg);
            txttotalh2.setText(tottharga);
            value_tgl_pem.setText(tgltrpem);
            ResultSet dbs = DB.query("SELECT nama_obat,harga,qty,subtotal from detail_pembelian join obat on detail_pembelian.kode_obat = obat.kode_obat where kode_transaksi='" + Kodetrx1 + "'");
            DefaultTableModel table2 = (DefaultTableModel) TBLdetail_2.getModel();
            table2.setRowCount(0);
            while (dbs.next()) {
                Object[] data = {
                    no,
                    dbs.getString("nama_obat"),
                    dbs.getString("harga"),
                    dbs.getString("qty"),
                    dbs.getString("subtotal")
                };
                table2.addRow(data);
                no++;
            }
            //
            
               form.pack();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void export() {
        try {
            // Koneksi ke database

            java.sql.Connection connection = DB.getConnection();
            // Query
            String sql = "SELECT * FROM laporan_pembelian order by tanggal_transaksi desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            // Membuat workbook Excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Datalaporan Pembelian");

            // Menambahkan header
            Row headerRow = sheet.createRow(0);
            int columnCount = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(result.getMetaData().getColumnName(i));
            }

            // Menambahkan data
            int rownum = 1;
            while (result.next()) {
                Row row = sheet.createRow(rownum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(result.getString(i));
                }
            }

            // Menyesuaikan lebar kolom
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy,HH-mm-ss");
            String timestamp = dateFormat.format(new Date());
            // Menyimpan ke file Excel
            String fp = System.getProperty("user.home") + "/Downloads/laporan pembelian" + timestamp + " .xlsx";
            FileOutputStream outputStream = new FileOutputStream(fp);
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(table, "Berhasil Disimpan" + fp);
            workbook.close();
            connection.close();
            outputStream.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
