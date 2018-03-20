package zzzTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by nibnait on 2016/11/5.
 */
public class DateFormatTest {
    private static final String LocalDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String LocalDateFormat = "yyyy-MM-dd";

    public static void main(String[] args) {
        System.out.println(toLocalDate(253392422400000l));



    }
    private static LocalDate toLocalDate(long time) {
        SimpleDateFormat sdf =  new SimpleDateFormat(LocalDateFormat);
        String d = sdf.format(time);
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern(LocalDateFormat);
        return LocalDate.parse(d, dtf);
    }

    private void teat01() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = "2016-11-01";
        String str2 = "2016-11-02";

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(str1);
            d2 = format.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(d1.compareTo(d2));
    }
}
