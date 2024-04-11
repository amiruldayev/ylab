package org.example.Repository;

import org.example.Model.Training;

import java.util.List;

public interface TrainingRepository {
    void addTraining(String username, Training training);
    List<Training> getAllTrainingsByUsername(String username);
    Training getTrainingById(String username, int trainingId);
    void updateTraining(String username, int trainingId, Training updatedTraining);
    void deleteTraining(String username, int trainingId);
    // Метод для получения всех тренировок
    List<Training> getAllTrainings();
}