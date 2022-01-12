package com.coindesk.test.coindesktool.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class AppUtils {

    
    public static LocalDateTime convertToSystemTimeZone(LocalDateTime toConvert ) {
        LocalDateTime lt = toConvert.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
        return lt;
    }
    
    public static LocalDateTime parseStringToLocalDateTime(String timeStr,String pattern) {
        
        DateTimeFormatter utcDateFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(utcDateFormatter.parse(timeStr));
        
    }
    
    public static LocalDateTime parseISODateStr(String isoTime) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                 // date/time
                .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                // offset (hh:mm - "+00:00" when it's zero)
                .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
                // offset (hhmm - "+0000" when it's zero)
                .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
                // offset (hh - "Z" when it's zero)
                .optionalStart().appendOffset("+HH", "Z").optionalEnd()
                // create formatter
                .toFormatter();
        OffsetDateTime off = OffsetDateTime.parse(isoTime,formatter);
        ZonedDateTime z = off.atZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime lt = z.toLocalDateTime();
        return lt;
    }
}
