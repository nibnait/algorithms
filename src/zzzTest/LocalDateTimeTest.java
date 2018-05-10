package zzzTest;

import utils.DateTimeUtil;

import java.time.*;
import java.util.TimeZone;

public class LocalDateTimeTest {
    public static void main(String[] args) {
//        System.out.println(DateTimeUtil.stringToLocalDateTime("2019-09-09", DateTimeUtil.DATE_FORMAT));
        System.out.println(DateTimeUtil.stringToLocalDate("2019-09-09 11:11:11", DateTimeUtil.DATE_TIME_FORMAT));
    }

    private static void testTimestamp() {
        Long timestamp = 1488187910l;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());
        System.out.println(localDateTime);
    }

    private static void test2() {
        LocalDateTime beginDate = LocalDate.now().minusDays(67).atStartOfDay();
        LocalDateTime endDate = LocalDate.now().minusDays(60).atStartOfDay();
        System.out.println(beginDate+"   "+endDate);
    }

    private static void test1() {
        System.out.println(LocalDateTime.now().getDayOfWeek());
        System.out.println(LocalDateTime.now().getDayOfMonth());

        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        System.out.println(1+dayOfWeek.getValue());
    }
}
