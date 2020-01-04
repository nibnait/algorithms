package common.util;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeUtil {

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now().minusMinutes(1);
        LocalDateTime end = LocalDateTime.now();
        TimeBetween timeBetween = calcTimeBetween(start, end);
        System.out.println(timeBetween.toString());
    }

    @Data
    static class TimeBetween{
        private long days;
        private long seconds;
    }

    /**
     * 计算时间差
     */
    public static TimeBetween calcTimeBetween(LocalDateTime start, LocalDateTime end) {
        TimeBetween timeBetween = new TimeBetween();
        timeBetween.setDays(ChronoUnit.DAYS.between(start, end));
        timeBetween.setSeconds(ChronoUnit.SECONDS.between(start, end));
        return timeBetween;
    }
}
