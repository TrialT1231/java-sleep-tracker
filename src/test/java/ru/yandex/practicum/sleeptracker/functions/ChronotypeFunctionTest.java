package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChronotypeFunctionTest {

    private final ChronotypeFunction function = new ChronotypeFunction();

    @Test
    void shouldDetectOwl() {
        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 9, 30),
                SleepQuality.GOOD);
        SleepingSession night2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 45),
                LocalDateTime.of(2025, 10, 3, 10, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(night1, night2));

        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void shouldDetectLark() {

        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD);
        SleepingSession night2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 30),
                LocalDateTime.of(2025, 10, 3, 6, 30),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(night1, night2));

        assertEquals(Chronotype.LARK, result.getValue());
    }

    @Test
    void shouldDefaultToDoveOnTie() {

        SleepingSession owlNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 9, 30),
                SleepQuality.GOOD);
        SleepingSession larkNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.GOOD);

        SleepAnalysisResult<?> result = function.apply(List.of(owlNight, larkNight));

        assertEquals(Chronotype.DOVE, result.getValue());
    }

    @Test
    void shouldIgnoreDaytimeNaps() {

        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD);
        SleepingSession night2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 30),
                LocalDateTime.of(2025, 10, 3, 6, 30),
                SleepQuality.GOOD);
        SleepingSession nap = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 14, 0),
                LocalDateTime.of(2025, 10, 3, 15, 0),
                SleepQuality.NORMAL);

        SleepAnalysisResult<?> result = function.apply(List.of(night1, night2, nap));

        assertEquals(Chronotype.LARK, result.getValue());
    }
}
