package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightsFunctionTest {

    private final SleeplessNightsFunction function = new SleeplessNightsFunction();

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult<?> result = function.apply(List.of());
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenEveryNightIsCovered() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(session));

        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldCountOneMissedNightBetweenTwoSessions() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(session1, session2));

        assertEquals(1L, result.getValue());
    }

    @Test
    void shouldHandleFirstSessionStartingBeforeNoon() {
        SleepingSession daytimeSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 5, 8, 0),
                LocalDateTime.of(2025, 10, 5, 9, 0),
                SleepQuality.NORMAL);

        SleepAnalysisResult<?> result = function.apply(List.of(daytimeSession));

        assertEquals(2L, result.getValue());
    }
}
