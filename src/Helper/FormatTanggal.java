/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Muhammad Nor Kholit
 */
public class FormatTanggal {

    public static String format(Timestamp tanggal) {
        // Tentukan format tanggal dan waktu beserta bahasa Indonesia
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss", new Locale("id", "ID"));

        // Konversi dan kembalikan hasilnya
        return sdf.format(tanggal);
    }

    public static String formatTanggal(String input) {
        DateTimeFormatter inputFormatter;

        if (input.length() == 7) {
            // Format untuk "yyyy-MM"
            inputFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));
            input = input + "-01";
        } else if (input.length() == 10) {
            // Format untuk "yyyy-MM-dd"
            inputFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        } else if (input.length() == 4) {
            // Format untuk "yyyy"
            inputFormatter = DateTimeFormatter.ofPattern("yyyy");
            input = input + "-01-01";

        } else {
            return "Format tanggal tidak valid";
        }

        return inputFormatter.format(LocalDate.parse(input));
     
    }

    public static String formatDate(Date tanggal) {
        // Tentukan format tanggal dan waktu beserta bahasa Indonesia
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));

        // Konversi dan kembalikan hasilnya
        return sdf.format(tanggal);
    }

    public static String formatMonth(Date tanggal) {
        // Tentukan format tanggal dan waktu beserta bahasa Indonesia
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("id", "ID"));

        // Konversi dan kembalikan hasilnya
        return sdf.format(tanggal);
    }

    public static String formatYear(Date tanggal) {
        // Tentukan format tanggal dan waktu beserta bahasa Indonesia
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy", new Locale("id", "ID"));

        // Konversi dan kembalikan hasilnya
        return sdf.format(tanggal);
    }
}
