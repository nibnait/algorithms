package common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    private static ZoneId zone = ZoneId.systemDefault();
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDate string2Date(String dateString) {
        if (dateString == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateString, formatter);
    }

    public static LocalDate dateStringDateToLocalDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } else {
            return LocalDate.now();
        }
    }

    private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static DateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private static ZoneId defaultZoneId = ZoneId.systemDefault();

    /**
     * String ==> Date
     */
    public static Date stringToDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    public static Date stringToDate(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.parse(dateString);
    }

    /**
     * Date ==> String
     */
    public static String date2String(Date date) {
        return dateFormat.format(date);
    }

    public static String date2String(Date date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * String ==> LocalDate
     */
    public static LocalDate stringToLocalDate(String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }

    public static LocalDate stringToLocalDate(String dateString, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(dateString, dateTimeFormatter);
    }

    /**
     * LocalDate ==> String
     */
    public static String localDateToString(LocalDate localDate) {
        return localDate.format(dateFormatter);
    }

    /**
     * String ==> LocalDateTime
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
    }

    public static LocalDateTime dateStringToLocalDateTime(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
        return localDate.atStartOfDay();
    }

    /**
     * LocalDateTime ==> String
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     *  Date
     *  LocalDate       ==>     java.util.Date
     *  LocalDateTime  ( Date.from(ZonedDateTime.toInstant) )
     */
    public static Date toUtilDate(Object date) {
        if (date == null) {
            return null;
        }
        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
            return new Date(((Date) date).getTime());
        }
        if (date instanceof Date) {
            return (Date) date;
        }
        if (date instanceof LocalDate) {
            return Date.from(((LocalDate) date).atStartOfDay(defaultZoneId).toInstant());
        }
        if (date instanceof LocalDateTime) {
            return Date.from(((LocalDateTime) date).atZone(defaultZoneId).toInstant());
        }
        if (date instanceof ZonedDateTime) {
            return Date.from(((ZonedDateTime) date).toInstant());
        }
        if (date instanceof Instant) {
            return Date.from((Instant) date);
        }

        throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
    }

    /**
     * Date ==> LocalDate
     *  .toIns
     *   (ZonedDateTime)
     */
    public static LocalDate dateToLocalDate(Date date) {
        ZonedDateTime zonedDateTime = date.toInstant().atZone(defaultZoneId);
        return zonedDateTime.toLocalDate();
    }

    /**
     * Date ==> LocalDateTime
     *   (ZonedDateTime)
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        ZonedDateTime zonedDateTime = date.toInstant().atZone(defaultZoneId);
        return zonedDateTime.toLocalDateTime();
    }

    public static final String LONG_TIME_VALID = "长期有效";
    /**
     * 获取当前时间戳
     */
    public static Long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 判断是否在有效期内
     * @param expireDate
     * @return
     */
    public static boolean isValidDate(String expireDate) {
        if(StringUtils.isBlank(expireDate)){
            return false;
        }
        if(LONG_TIME_VALID.equals(expireDate)){
            return true;
        }
        try{
            LocalDate expireLocalDate = DateTimeUtil.string2Date(expireDate);
            if(LocalDate.now().compareTo(expireLocalDate) < 0){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 比较两个日期相差多少天
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long durationDays(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).toDays();
    }
}
