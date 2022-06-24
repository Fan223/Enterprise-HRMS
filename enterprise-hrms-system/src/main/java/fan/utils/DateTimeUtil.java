package fan.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/3/14 9:52
 * @Version 1.0
 */
public class DateTimeUtil {

    public static String parseToSimpleString(LocalDateTime localDateTime) {

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String parseToFullString(LocalDateTime localDateTime) {

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime parseToLocalDateTime(String stringTime) {

        LocalDateTime parse = LocalDateTime.parse(stringTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

        ZonedDateTime zonedDateTime = parse.atZone(ZoneId.from(ZoneOffset.UTC));
        ZonedDateTime convertTime  = zonedDateTime.withZoneSameInstant(ZoneOffset.ofHours(8));

        return convertTime.toLocalDateTime();
    }

    public static LocalDateTime parseToLocalDate(String stringTime) {
        LocalDate localDate = LocalDate.parse(stringTime);

        return localDate.atStartOfDay();
    }

    public static String parseToYearMonth(LocalDateTime localDateTime) {

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
}

