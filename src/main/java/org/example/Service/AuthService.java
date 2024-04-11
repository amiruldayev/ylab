package org.example.Service;

import org.example.Model.User;

public interface AuthService {
    User register(String username, String password, boolean isAdmin);
    User login(String username, String password);
}
