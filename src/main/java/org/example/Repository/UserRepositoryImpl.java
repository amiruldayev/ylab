package org.example.Repository;

import org.example.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> usersByUsername;

    public UserRepositoryImpl() {
        this.usersByUsername = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        usersByUsername.put(user.getUsername(), user);
    }

    @Override
    public User getUserByUsername(String username) {
        return usersByUsername.get(username);
    }
}