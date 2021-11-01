package common.util;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by nibnait on 2021/09/08
 */
public class DateTimeCalcUtils {

    /**
     * 计算时间差（start - end）
     */
    public static TimeBetween calcTimeBetween(LocalDateTime start, LocalDateTime end) {
        TimeBetween timeBetween = new TimeBetween();
        timeBetween.setYears(ChronoUnit.YEARS.between(end, start));
        timeBetween.setMounths(ChronoUnit.MONTHS.between(end, start));
        timeBetween.setDays(ChronoUnit.DAYS.between(end, start));
        timeBetween.setHours(ChronoUnit.HOURS.between(end, start));
        timeBetween.setMinutes(ChronoUnit.MINUTES.between(end, start));
        timeBetween.setSeconds(ChronoUnit.SECONDS.between(end, start));
        timeBetween.setMillis(ChronoUnit.MILLIS.between(end, start));
        return timeBetween;
    }

    @Data
    public static class TimeBetween {
        private Long years;
        private Long mounths;
        private Long days;
        private Long hours;
        private Long minutes;
        private Long seconds;
        private Long millis;
    }


}
