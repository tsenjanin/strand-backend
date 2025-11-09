package me.strand.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateUtils {
    public LocalDateTime getDateTimeCurrent() {
        return LocalDateTime.now();
    }

    public LocalDateTime getDateTimeAfter(Long days) {
        return LocalDateTime.now().plusDays(days);
    }
}
