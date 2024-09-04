package com.github.kosmateus.shinden.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtils {

    public static long convertLocalDateToSeconds(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
    }
}
