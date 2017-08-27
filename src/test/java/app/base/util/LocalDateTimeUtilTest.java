package app.base.util;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LocalDateTimeUtilTest {

    @Test
    public void String2LocalDateTime() {
        String dateTime = "20170101 010101";
        String format = "yyyMMdd HHmmss";
        LocalDateTime localDateTime = LocalDateTimeUtil.from(dateTime, format);
        assertEquals(localDateTime.getMinute(), 1);
        assertNotNull(localDateTime);
    }

    @Test
    public void String2LocalDateTime_withEmptyDateTime() {
        String dateTime = "";
        String format = "yyyMMdd HHmmss";
        LocalDateTime localDateTime = LocalDateTimeUtil.from(dateTime, format);
        assertNotNull(localDateTime);
        assertEquals(localDateTime.getMinute(), LocalDateTime.now().getMinute());
    }

    @Test
    public void String2LocalDateTime_butDateTimeIsInDiffFormat() {
        LocalDateTime now = LocalDateTime.now();
        String dateTime = "2017-01-01 010101";
        String format = "yyyMMdd HHmmss";
        LocalDateTime localDateTime = LocalDateTimeUtil.from(dateTime, format);
        assertNotNull(localDateTime);
        assertTrue(localDateTime.compareTo(now) >= 0);
    }

    @Test
    public void String2LocalDateTime_butInvalidDateFormat() {
        LocalDateTime now = LocalDateTime.now();
        String dateTime = "20170101 010101";
        String format = "Invalid format";
        LocalDateTime localDateTime = LocalDateTimeUtil.from(dateTime, format);
        assertNotNull(localDateTime);
        assertTrue(localDateTime.compareTo(now) >= 0);
    }
}
