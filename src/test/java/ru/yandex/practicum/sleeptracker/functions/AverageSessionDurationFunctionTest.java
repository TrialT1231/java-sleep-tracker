package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageSessionDurationFunctionTest {

    private final AverageSessionDurationFunction function = new AverageSessionDurationFunction();

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult<?> result = function.apply(List.of());
        assertEquals(0.0, (double) result.getValue());
    }

    @Test
    void shouldComputeAverageOfTwoSessions() {
        // Сессии по 60 и 120 минут -> среднее 90.
        SleepingSession first = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 1, 23, 0),
                SleepQuality.GOOD);
        SleepingSession second = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 0, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(first, second));

        assertEquals(90.0, (double) result.getValue());
    }
}
