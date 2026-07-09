package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

    private static final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult<?> apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>(DESCRIPTION, 0L);
        }

        SleepingSession firstSession = sessions.get(0);
        SleepingSession lastSession = sessions.get(sessions.size() - 1);

        LocalDate firstNight = firstNightOf(firstSession);
        LocalDate lastNight = lastSession.getEnd().toLocalDate();

        long sleeplessCount = Stream.iterate(firstNight, date -> !date.isAfter(lastNight), date -> date.plusDays(1))
                .filter(night -> sessions.stream().noneMatch(session -> session.overlapsNightOf(night)))
                .count();

        return new SleepAnalysisResult<>(DESCRIPTION, sleeplessCount);
    }

    private LocalDate firstNightOf(SleepingSession firstSession) {
        LocalDate startDate = firstSession.getStart().toLocalDate();
        int startHour = firstSession.getStart().getHour();
        if (startHour >= 12) {
            return startDate.plusDays(1);
        } else {
            return startDate.minusDays(1);
        }
    }
}
