package common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nibnait on 2022/03/21
 */
@Slf4j
public class DateConvertUtils {

    public static final String TIME_INTERVAL_SECOND = "second";
    public static final String TIME_INTERVAL_MINUTE = "minute";
    public static final String TIME_INTERVAL_HOUR = "hour";
    public static final String TIME_INTERVAL_DAY = "day";
    public static final String TIME_INTERVAL_WEEK = "week";
    public static final String TIME_INTERVAL_MONTH = "month";
    public static final String TIME_INTERVAL_QUARTER = "quarter";
    public static final String TIME_INTERVAL_YEAR = "year";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";



    public static String format(Date date, String dateFormat) {
        if (date == null)
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static String getDateString(String datePattern) {
        return new SimpleDateFormat(datePattern).format(DateUtils.now());
    }

    public static String getYesterdayString(String dateFormat) {
        Date yesterday = add(Calendar.DAY_OF_YEAR, DateUtils.now(), -1);
        return DateConvertUtils.format(yesterday, dateFormat);
    }

    public static Date add(int field, Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int fieldNewValue = (c.get(field) + value);
        c.set(field, fieldNewValue);
        return c.getTime();
    }

    public static long dateDiff(String timeInterval, Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        if (timeInterval.equals(TIME_INTERVAL_YEAR)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(date2);
            return (long)time - calendar.get(Calendar.YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_QUARTER)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH) / 4;
            calendar.setTime(date2);
            return (long)time - calendar.get(Calendar.MONTH) / 4;
        }

        if (timeInterval.equals(TIME_INTERVAL_MONTH)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(date2);
            return (long)time - calendar.get(Calendar.MONTH);
        }

        if (timeInterval.equals(TIME_INTERVAL_WEEK)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date1);
            time += calendar.get(Calendar.WEEK_OF_YEAR);
            calendar.setTime(date2);
            return (long)time - calendar.get(Calendar.WEEK_OF_YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_DAY)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.DAY_OF_YEAR)
                    + calendar.get(Calendar.YEAR) * 365;
            calendar.setTime(date2);
            return (long)time
                    - (calendar.get(Calendar.DAY_OF_YEAR) + calendar
                    .get(Calendar.YEAR) * 365);
        }

        if (timeInterval.equals(TIME_INTERVAL_HOUR)) {
            long time = date1.getTime() / 1000 / 60 / 60;
            return time - date2.getTime() / 1000 / 60 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_MINUTE)) {
            long time = date1.getTime() / 1000 / 60;
            return time - date2.getTime() / 1000 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_SECOND)) {
            long time = date1.getTime() / 1000;
            return time - date2.getTime() / 1000;
        }

        return date1.getTime() - date2.getTime();
    }

    public static long dateDiff(String timeInterval, Long unixTime1,
                                Long unixTime2) {
        return dateDiff(timeInterval, new Date(unixTime1), new Date(unixTime2));
    }

    public static void main(String[] args) {
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static int getCurrentTime(){
        return DateUtils.currentTime();
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStampToDate(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = DATE_TIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

}
