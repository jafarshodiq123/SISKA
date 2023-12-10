package Helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Notification {

    public static void showSuccess(String message,Component parent) {
        showMessage(parent,message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(String message,Component parent) {
        showMessage(parent,message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessage(Component parent,String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }

    public static void showInfo(String message,Component parent) {
        showMessage(parent,message, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showConfirmDelete(Component parent) {

        int result = JOptionPane.showConfirmDialog(
                parent,
                "Anda yakin ingin menghapus data?",
                "Konfirmasi Hapus Data",
                JOptionPane.YES_NO_OPTION
        );

        return result == JOptionPane.YES_OPTION;
    }

    // Success messages
    public static final String DATA_ADDED_SUCCESS = "Data berhasil ditambahkan.";
    public static final String DATA_UPDATED_SUCCESS = "Data berhasil diperbarui.";
    public static final String DATA_DELETED_SUCCESS = "Data berhasil dihapus.";

    // Error messages
    public static final String SERVER_ERROR = "Terjadi error pada server. Mohon coba lagi nanti";
    public static final String EMPTY_VALUE = "Field tidak boleh kosong ";
    public static final String DUPLICATE_DATA = "Data sudah ada.";
    public static final String DATA_ADD_ERROR = "Gagal menambahkan data.";
    public static final String DATA_UPDATE_ERROR = "Gagal memperbarui data.";
    public static final String DATA_DELETE_ERROR = "Gagal menghapus data.";
    public static final String DATA_IN_USE_ERROR = "Gagal menghapus data. Data ini digunakan di bagian lain sistem.";
    public static final String TRY_AGAIN_LATER_ERROR = "Gagal menghapus data. Mohon coba lagi nanti.";

    //Information message
    public static final String NO_DATA_SELECTED_INFO = "Tidak ada data yang dipilih. Pilih data terlebih dahulu.";

}
