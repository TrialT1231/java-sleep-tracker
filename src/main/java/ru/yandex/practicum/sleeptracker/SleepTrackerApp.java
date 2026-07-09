package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.AverageSessionDurationFunction;
import ru.yandex.practicum.sleeptracker.functions.BadQualityCountFunction;
import ru.yandex.practicum.sleeptracker.functions.ChronotypeFunction;
import ru.yandex.practicum.sleeptracker.functions.CountSessionsFunction;
import ru.yandex.practicum.sleeptracker.functions.MaxSessionDurationFunction;
import ru.yandex.practicum.sleeptracker.functions.MinSessionDurationFunction;
import ru.yandex.practicum.sleeptracker.functions.SleeplessNightsFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private final List<Function<List<SleepingSession>, SleepAnalysisResult<?>>> analysisFunctions = List.of(
            new CountSessionsFunction(),
            new MinSessionDurationFunction(),
            new MaxSessionDurationFunction(),
            new AverageSessionDurationFunction(),
            new BadQualityCountFunction(),
            new SleeplessNightsFunction(),
            new ChronotypeFunction()
    );

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Укажите путь к файлу с логом сна первым аргументом командной строки.");
            return;
        }

        List<SleepingSession> sessions = loadSessions(args[0]);

        SleepTrackerApp app = new SleepTrackerApp();

        app.analysisFunctions.forEach(function -> {
            SleepAnalysisResult<?> result = function.apply(sessions);
            System.out.println(result);
        });
    }

    private static List<SleepingSession> loadSessions(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath)).stream()
                .filter(line -> !line.isBlank())
                .map(SleepingSession::parse)
                .toList();
    }
}
