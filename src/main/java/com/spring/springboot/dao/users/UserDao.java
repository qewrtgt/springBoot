package com.spring.springboot.dao.users;



import com.spring.springboot.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void updateUser(User user);

    public User getUserById(long id);

    public void deleteUser(User user);

    public void deleteUserById(long id);

    public User getUserFirstName(String firstname);
}
