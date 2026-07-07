package ru.yandex.practicum.sleeptracker;

public enum Chronotype {
    OWL("Сова"),
    LARK("Жаворонок"),
    DOVE("Голубь");

    private final String displayName;

    // Конструктор enum — вызывается автоматически при создании констант выше.
    Chronotype(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
