package game.utils;

import game.exception.ParseDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static LocalDate parse(String strDate) throws ParseDateException {
        try {
            LocalDate date = simpleDateFormat.parse(strDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return date;
        } catch (ParseException parseException) {
            throw new ParseDateException("Lỗi định dạng ngày tháng năm.");
        }
    }
}
