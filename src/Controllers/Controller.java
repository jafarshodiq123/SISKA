package Controllers;

import javax.swing.JTable;
/**
 *
 * @author Muhammad Nor Kholit
 */
interface Controller {
    
    public  void tampilData();
    public void tambahData(Object[] object);
    public void simpanData(Object[] object);
    public void editData(Object[] id);
    public void updateData(Object[] object);
    public void hapusData(Object[] object);
}
