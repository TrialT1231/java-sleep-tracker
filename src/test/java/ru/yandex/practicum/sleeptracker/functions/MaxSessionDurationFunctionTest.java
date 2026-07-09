package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSessionDurationFunctionTest {

    private final MaxSessionDurationFunction function = new MaxSessionDurationFunction();

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult<?> result = function.apply(List.of());
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldFindLongestSession() {
        SleepingSession longSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD);
        SleepingSession shortNap = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 14, 0),
                LocalDateTime.of(2025, 10, 2, 14, 50),
                SleepQuality.NORMAL);

        SleepAnalysisResult<?> result = function.apply(List.of(longSession, shortNap));

        assertEquals(600L, result.getValue());
    }
}
