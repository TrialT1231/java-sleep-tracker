package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSessionsFunctionTest {

    private final CountSessionsFunction function = new CountSessionsFunction();

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult<?> result = function.apply(List.of());
        assertEquals(0, result.getValue());
    }

    @Test
    void shouldCountTwoSessions() {
        SleepingSession first = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 15),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD);
        SleepingSession second = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 8, 0),
                SleepQuality.NORMAL);

        SleepAnalysisResult<?> result = function.apply(List.of(first, second));

        assertEquals(2, result.getValue());
    }
}
