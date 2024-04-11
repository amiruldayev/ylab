package org.example.Model;

import java.util.Date;

public class Training {
    private static int nextId = 1;
    private int id;
    private Date date;
    private TrainingType type;
    private int durationInMinutes;
    private int caloriesBurned;
    private int exerciseCount; // количество выполненных упражнений
    private double distance; // расстояние

    public Training(Date date, TrainingType type, int durationInMinutes, int caloriesBurned) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
        this.exerciseCount = exerciseCount;
        this.distance = distance;
    }

    // Геттеры и сеттеры для полей

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    public int getExerciseCount() {
        return exerciseCount;
    }

    public void setExerciseCount(int exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Переопределение метода toString для удобного вывода информации о тренировке

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", date=" + date +
                ", type=" + type +
                ", durationInMinutes=" + durationInMinutes +
                ", caloriesBurned=" + caloriesBurned +
                ", exerciseCount=" + exerciseCount +
                ", distance=" + distance +
                '}';
    }
}
