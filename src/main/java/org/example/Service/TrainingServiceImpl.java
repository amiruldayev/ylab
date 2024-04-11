package org.example.Service;

import org.example.Model.Training;
import org.example.Model.TrainingType;
import org.example.Repository.TrainingRepository;

import java.util.Date;
import java.util.List;

public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void addTraining(String username, Date date, String type, int durationInMinutes, int caloriesBurned) {

    }

    @Override
    public void addTraining(String username, Date date, TrainingType type, int durationInMinutes, int caloriesBurned, int exerciseCount, double distance) {
        Training training = new Training(date, type, durationInMinutes, caloriesBurned);
        trainingRepository.addTraining(username, training);
    }

    @Override
    public List<Training> getAllTrainingsByUsername(String username) {
        return trainingRepository.getAllTrainingsByUsername(username);
    }

    @Override
    public Training getTrainingById(String username, int trainingId) {
        return trainingRepository.getTrainingById(username, trainingId);
    }

    @Override
    public void updateTraining(String username, int trainingId, Date date, String type, int durationInMinutes, int caloriesBurned) {

    }

    @Override
    public void updateTraining(String username, int trainingId, Date date, TrainingType type, int durationInMinutes, int caloriesBurned) {
        Training training = new Training(date, type, durationInMinutes, caloriesBurned);
        trainingRepository.updateTraining(username, trainingId, training);
    }

    @Override
    public void deleteTraining(String username, int trainingId) {
        trainingRepository.deleteTraining(username, trainingId);
    }

    @Override
    public double getTotalCaloriesBurned(String username) {
        List<Training> trainings = trainingRepository.getAllTrainingsByUsername(username);
        double totalCalories = 0;
        for (Training training : trainings) {
            totalCalories += training.getCaloriesBurned();
        }
        return totalCalories;
    }

    @Override
    public List<Training> getAllTrainings() {
        return null;
    }

    @Override
    public boolean editTraining(int trainingId, Date date, TrainingType type, int durationInMinutes, int caloriesBurned, int exerciseCount, double distance) {
        return false;
    }

    @Override
    public boolean deleteTraining(int trainingId) {
        return false;
    }
}
