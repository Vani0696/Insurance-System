package com.insurance.controller;

import com.insurance.model.User;
import java.util.List;

public interface UserController {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
    User getUser(String username);
    List<User> getAllUsers();
}
