package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleepTrackerAppTest {

    @Test
    void shouldParseSessionLineFromLogFile() {
        SleepingSession session = SleepingSession.parse("01.10.25 22:15;02.10.25 08:00;GOOD");

        assertEquals(LocalDateTime.of(2025, 10, 1, 22, 15), session.getStart());
        assertEquals(LocalDateTime.of(2025, 10, 2, 8, 0), session.getEnd());
        assertEquals(SleepQuality.GOOD, session.getQuality());
    }
}
