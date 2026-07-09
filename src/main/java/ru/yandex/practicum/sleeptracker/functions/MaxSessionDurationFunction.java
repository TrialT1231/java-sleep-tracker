package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MaxSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

    private static final String DESCRIPTION = "Максимальная продолжительность сессии (мин)";

    @Override
    public SleepAnalysisResult<?> apply(List<SleepingSession> sessions) {
        long maxMinutes = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .max()
                .orElse(0);
        return new SleepAnalysisResult<>(DESCRIPTION, maxMinutes);
    }
}
