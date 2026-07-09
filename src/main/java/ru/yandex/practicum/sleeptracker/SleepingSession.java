package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SleepingSession {

    private static final DateTimeFormatter FILE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public static SleepingSession parse(String line) {
        String[] parts = line.split(";");
        LocalDateTime start = LocalDateTime.parse(parts[0].trim(), FILE_FORMAT);
        LocalDateTime end = LocalDateTime.parse(parts[1].trim(), FILE_FORMAT);
        SleepQuality quality = SleepQuality.valueOf(parts[2].trim());
        return new SleepingSession(start, end, quality);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    public boolean overlapsNightOf(LocalDate date) {
        LocalDateTime nightStart = date.atTime(0, 0);
        LocalDateTime nightEnd = date.atTime(6, 0);
        return start.isBefore(nightEnd) && end.isAfter(nightStart);
    }
}
