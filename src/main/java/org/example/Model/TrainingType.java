package org.example.Model;

public enum TrainingType {
    CARDIO("Кардио"),
    STRENGTH("Силовая тренировка"),
    YOGA("Йога");
    // Другие типы тренировок

    private final String description;

    TrainingType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
