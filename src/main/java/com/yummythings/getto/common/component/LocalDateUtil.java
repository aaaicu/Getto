package com.yummythings.getto.common.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    public static String LocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String LocalDateTimeToKorString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"));
    }
}
