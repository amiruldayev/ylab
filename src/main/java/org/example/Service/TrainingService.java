package org.example.Service;

import org.example.Model.Training;
import org.example.Model.TrainingType;

import java.util.Date;
import java.util.List;

public interface TrainingService {
    void addTraining(String username, Date date, String type, int durationInMinutes, int caloriesBurned);

    void addTraining(String username, Date date, TrainingType type, int durationInMinutes, int caloriesBurned, int exerciseCount, double distance);

    List<Training> getAllTrainingsByUsername(String username);
    Training getTrainingById(String username, int trainingId);
    void updateTraining(String username, int trainingId, Date date, String type, int durationInMinutes, int caloriesBurned);

    void updateTraining(String username, int trainingId, Date date, TrainingType type, int durationInMinutes, int caloriesBurned);

    void deleteTraining(String username, int trainingId);
    double getTotalCaloriesBurned(String username);
    // Метод для получения всех тренировок
    List<Training> getAllTrainings();

    boolean editTraining(int trainingId, Date date, TrainingType type, int durationInMinutes, int caloriesBurned, int exerciseCount, double distance);
    boolean deleteTraining(int trainingId);
}