package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

    private static final LocalTime LATE_BEDTIME = LocalTime.of(23, 0);
    private static final LocalTime LATE_WAKEUP = LocalTime.of(9, 0);
    private static final LocalTime EARLY_BEDTIME = LocalTime.of(22, 0);
    private static final LocalTime EARLY_WAKEUP = LocalTime.of(7, 0);

    @Override
    public SleepAnalysisResult<?> apply(List<SleepingSession> sessions) {
        List<SleepingSession> nightSessions = sessions.stream()
                .filter(this::isNightSession)
                .toList();

        long owlCount = nightSessions.stream().filter(this::isOwlNight).count();
        long larkCount = nightSessions.stream().filter(this::isLarkNight).count();
        long doveCount = nightSessions.size() - owlCount - larkCount;

        Chronotype chronotype;
        if (owlCount > larkCount && owlCount > doveCount) {
            chronotype = Chronotype.OWL;
        } else if (larkCount > owlCount && larkCount > doveCount) {
            chronotype = Chronotype.LARK;
        } else {
            chronotype = Chronotype.DOVE;
        }

        return new SleepAnalysisResult<>("Хронотип пользователя", chronotype);
    }

    private boolean isNightSession(SleepingSession session) {
        return session.overlapsNightOf(session.getStart().toLocalDate())
                || session.overlapsNightOf(session.getEnd().toLocalDate());
    }

    private boolean isOwlNight(SleepingSession session) {
        LocalTime bedTime = session.getStart().toLocalTime();
        LocalTime wakeTime = session.getEnd().toLocalTime();
        return bedTime.isAfter(LATE_BEDTIME) && wakeTime.isAfter(LATE_WAKEUP);
    }

    private boolean isLarkNight(SleepingSession session) {
        LocalTime bedTime = session.getStart().toLocalTime();
        LocalTime wakeTime = session.getEnd().toLocalTime();
        return bedTime.isBefore(EARLY_BEDTIME) && wakeTime.isBefore(EARLY_WAKEUP);
    }
}
