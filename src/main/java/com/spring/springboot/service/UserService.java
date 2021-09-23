package com.spring.springboot.service;

import com.spring.springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user, String[] selectedRoles);

    User getUserById(int id);

    void deleteUser(User user);

    void deleteUserById(int id);

    User getUserByEmail(String email);

    String getRolesString(User user);

}
