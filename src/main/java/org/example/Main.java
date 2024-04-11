package org.example;

import org.example.Model.Training;
import org.example.Model.TrainingType;
import org.example.Model.User;
import org.example.Repository.TrainingRepository;
import org.example.Repository.TrainingRepositoryImpl;
import org.example.Repository.UserRepository;
import org.example.Repository.UserRepositoryImpl;
import org.example.Service.AuthService;
import org.example.Service.AuthServiceImpl;
import org.example.Service.TrainingService;
import org.example.Service.TrainingServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем репозиторий пользователей
        UserRepository userRepository = new UserRepositoryImpl();

        // Создаем репозиторий тренировок
        TrainingRepository trainingRepository = new TrainingRepositoryImpl();

        // Создаем сервис аутентификации и тренировок
        AuthService authService = new AuthServiceImpl(userRepository);
        TrainingService trainingService = new TrainingServiceImpl(trainingRepository);

        // Инициализируем Scanner для чтения с консоли
        Scanner scanner = new Scanner(System.in);

        try {
            // Регистрация нового пользователя
            System.out.println("Регистрация нового пользователя.");
            System.out.print("Введите имя пользователя: ");
            String username = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            // По умолчанию новый пользователь не администратор
            boolean isAdmin = false;
            User newUser = authService.register(username, password, isAdmin);
            if (newUser != null) {
                System.out.println("Регистрация прошла успешно.");

                // Вход пользователя
                System.out.println("\nВход в систему.");
                System.out.print("Введите имя пользователя: ");
                username = scanner.nextLine();
                System.out.print("Введите пароль: ");
                password = scanner.nextLine();
                User loggedInUser = authService.login(username, password);
                if (loggedInUser != null) {
                    System.out.println("Вход выполнен успешно. Добро пожаловать, " + loggedInUser.getUsername() + "!");
                    boolean isAdminUser = loggedInUser.isAdmin();
                    // Если пользователь является администратором, предоставляем ему возможность просмотра тренировок всех пользователей
                    if (isAdminUser) {
                        System.out.println("\nВыберите действие:");
                        System.out.println("1. Просмотреть тренировки всех пользователей");
                        System.out.println("2. Добавить тренировку");
                        int adminChoice = Integer.parseInt(scanner.nextLine());
                        switch (adminChoice) {
                            case 1:
                                // Получаем все тренировки всех пользователей
                                List<Training> allTrainings = trainingService.getAllTrainings();
                                if (allTrainings.isEmpty()) {
                                    System.out.println("Нет доступных тренировок.");
                                } else {
                                    System.out.println("Все тренировки:");
                                    for (Training training : allTrainings) {
                                        System.out.println(training);
                                    }
                                }
                                break;
                            case 2:
                                // Реализация добавления тренировки администратором
                                break;
                            default:
                                System.out.println("Неправильный выбор.");
                                break;
                        }
                    } else {
                        // Если пользователь не администратор, выполняем стандартные операции для пользователя
                        // Добавление тренировки, просмотр своих тренировок и т.д.
                        addTraining(scanner, loggedInUser, trainingService);
                    }
                } else {
                    System.out.println("Ошибка входа. Проверьте правильность имени пользователя и пароля.");
                }
            } else {
                System.out.println("Ошибка регистрации. Пользователь с таким именем уже существует.");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            // Закрываем Scanner
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void addTraining(Scanner scanner, User user, TrainingService trainingService) {
        do {
            System.out.println("\nДобавление новой тренировки.");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.print("Введите дату тренировки (yyyy-MM-dd): ");
            String dateString = scanner.nextLine();
            Date date;
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Неверный формат даты.");
                continue; // Продолжаем цикл, чтобы запросить новую дату
            }
            System.out.println("Выберите тип тренировки:");
            for (TrainingType type : TrainingType.values()) {
                System.out.println(type.name() + " - " + type.getDescription());
            }
            System.out.print("Введите тип тренировки: ");
            String typeString = scanner.nextLine();
            TrainingType type;
            try {
                type = TrainingType.valueOf(typeString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный тип тренировки.");
                continue; // Продолжаем цикл, чтобы запросить новый тип тренировки
            }
            System.out.print("Введите продолжительность тренировки (в минутах): ");
            int durationInMinutes = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите количество сожженных калорий: ");
            int caloriesBurned = Integer.parseInt(scanner.nextLine());

            // Дополнительные поля в зависимости от типа тренировки
            int exerciseCount = 0;
            double distance = 0.0;
            switch (type) {
                case YOGA:
                case STRENGTH:
                    System.out.print("Введите количество выполненных упражнений: ");
                    exerciseCount = Integer.parseInt(scanner.nextLine());
                    break;
                case CARDIO:
                    System.out.print("Введите расстояние (в км): ");
                    distance = Double.parseDouble(scanner.nextLine());
                    break;
            }

            // Добавление тренировки
            trainingService.addTraining(user.getUsername(), date, type, durationInMinutes, caloriesBurned, exerciseCount, distance);

            // Просмотр всех тренировок пользователя
            System.out.println("\nВсе тренировки пользователя " + user.getUsername() + ":");
            List<Training> userTrainings = trainingService.getAllTrainingsByUsername(user.getUsername());
            for (Training training : userTrainings) {
                System.out.println(training);
            }

            // Получение статистики по тренировкам
            double totalCaloriesBurned = trainingService.getTotalCaloriesBurned(user.getUsername());
            System.out.println("\nОбщее количество сожженных калорий: " + totalCaloriesBurned);

            // Запрос на продолжение или завершение
            System.out.print("\nХотите записать еще одну тренировку? (да/нет): ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("да")) {
                break;
            }
        } while (true);
    }
}