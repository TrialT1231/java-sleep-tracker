package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AverageSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

    private static final String DESCRIPTION = "Средняя продолжительность сессии (мин)";

    @Override
    public SleepAnalysisResult<?> apply(List<SleepingSession> sessions) {
        double averageMinutes = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .average()
                .orElse(0);
        return new SleepAnalysisResult<>(DESCRIPTION, averageMinutes);
    }
}
