package org.example.Repository;

import org.example.Model.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByUsername(String username);
}