package zzzTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nibnait on 2016/11/5.
 */
public class DateFormatTest {
    public static void main(String[] args) {
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
