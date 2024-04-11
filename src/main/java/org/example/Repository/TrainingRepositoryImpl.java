package org.example.Repository;

import org.example.Model.Training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingRepositoryImpl implements TrainingRepository {
    private final Map<String, List<Training>> trainingData = new HashMap<>();
    private int nextTrainingId = 1;

    @Override
    public void addTraining(String username, Training training) {
        List<Training> userTrainings = trainingData.computeIfAbsent(username, k -> new ArrayList<>());
        training.setId(nextTrainingId++);
        userTrainings.add(training);
    }

    @Override
    public List<Training> getAllTrainingsByUsername(String username) {
        return trainingData.getOrDefault(username, new ArrayList<>());
    }

    @Override
    public Training getTrainingById(String username, int trainingId) {
        List<Training> userTrainings = trainingData.get(username);
        if (userTrainings != null) {
            for (Training training : userTrainings) {
                if (training.getId() == trainingId) {
                    return training;
                }
            }
        }
        return null;
    }

    @Override
    public void updateTraining(String username, int trainingId, Training updatedTraining) {
        List<Training> userTrainings = trainingData.get(username);
        if (userTrainings != null) {
            for (int i = 0; i < userTrainings.size(); i++) {
                Training training = userTrainings.get(i);
                if (training.getId() == trainingId) {
                    updatedTraining.setId(trainingId);
                    userTrainings.set(i, updatedTraining);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteTraining(String username, int trainingId) {
        List<Training> userTrainings = trainingData.get(username);
        if (userTrainings != null) {
            userTrainings.removeIf(training -> training.getId() == trainingId);
        }
    }

    @Override
    public List<Training> getAllTrainings() {
        return null;
    }
}
