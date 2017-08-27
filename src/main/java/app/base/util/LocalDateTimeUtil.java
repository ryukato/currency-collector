package app.base.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    private LocalDateTimeUtil() {}

    /**
     * If the given dateTime is null or empty, then returns LocalDateTime.now(), otherwise returns LocalDateTime object for given dateTime in the given format.
     * But there is exception during the parsing the given dateTime, then returns LocalDateTime.now().
     * @param dateTime
     * @param format
     * @return
     */
    public static LocalDateTime from(String dateTime, String format) {
        LocalDateTime now = LocalDateTime.now();
        if (dateTime == null || dateTime.isEmpty()) {
            return now;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateTime, formatter);
        }catch (RuntimeException e) {
            return now;
        }

    }
}
