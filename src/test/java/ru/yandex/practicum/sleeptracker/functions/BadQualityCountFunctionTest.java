package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadQualityCountFunctionTest {

    private final BadQualityCountFunction function = new BadQualityCountFunction();

    @Test
    void shouldReturnZeroWhenNoBadSessions() {
        SleepingSession good = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(good));

        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldCountOnlyBadSessions() {
        SleepingSession bad = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.BAD);
        SleepingSession good = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.GOOD);
        SleepingSession normal = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 22, 0),
                LocalDateTime.of(2025, 10, 4, 6, 0),
                SleepQuality.NORMAL);

        SleepAnalysisResult<?> result = function.apply(List.of(bad, good, normal));

        assertEquals(1L, result.getValue());
    }
}
