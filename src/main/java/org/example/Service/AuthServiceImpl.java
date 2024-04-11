package org.example.Service;

import org.example.Model.User;
import org.example.Repository.UserRepository;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String password, boolean isAdmin) {
        // Проверка на существование пользователя с таким же именем
        if (userRepository.getUserByUsername(username) != null) {
            System.out.println("Пользователь с таким именем уже существует.");
            return null;
        }

        // Создание нового пользователя
        User user = new User(username, password, isAdmin);
        userRepository.addUser(user);
        System.out.println("Пользователь успешно зарегистрирован.");
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.getUserByUsername(username);

        // Проверка на существование пользователя с указанным именем
        if (user == null) {
            System.out.println("Пользователь с таким именем не найден.");
            return null;
        }

        // Проверка правильности пароля
        if (!user.getPassword().equals(password)) {
            System.out.println("Неправильный пароль.");
            return null;
        }

        System.out.println("Вход выполнен успешно.");
        return user;
    }
}
