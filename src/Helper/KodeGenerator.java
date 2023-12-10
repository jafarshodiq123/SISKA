package Helper;

import java.util.UUID;

public class KodeGenerator {

    public static String generateKodeSuplier() {
        return generateKode("SUP");
    }

    public static String generateKodeObat() {
        return generateKode("OBT");
    }

    public static String generateKodeTransaksi() {
        return generateKode("TRX");
    }

    private static String generateKode(String prefix) {
        // Menggunakan 8 karakter pertama dari UUID dan menggabungkannya dengan prefix
        String uuidPart = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        return prefix + uuidPart;
    }

}
