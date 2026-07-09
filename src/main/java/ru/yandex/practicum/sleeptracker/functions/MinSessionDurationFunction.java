package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MinSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

    private static final String DESCRIPTION = "Минимальная продолжительность сессии (мин)";

    @Override
    public SleepAnalysisResult<?> apply(List<SleepingSession> sessions) {
        long minMinutes = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .min()
                .orElse(0);
        return new SleepAnalysisResult<>(DESCRIPTION, minMinutes);
    }
}
