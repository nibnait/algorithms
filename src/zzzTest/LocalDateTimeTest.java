package zzzTest;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LocalDateTimeTest {
    public static void main(String[] args) {
//        test1();
        LocalDateTime beginDate = LocalDate.now().minusDays(67).atStartOfDay();
        System.out.println(beginDate);

    }

    private static void testTimestamp() {
        Long timestamp = 1488187910l;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());
        System.out.println(localDateTime);
    }

    private static void test1() {
        System.out.println(LocalDateTime.now().getDayOfWeek());
        System.out.println(LocalDateTime.now().getDayOfMonth());

        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        System.out.println(1+dayOfWeek.getValue());
    }
}
