package com.springboot6.springboot6.dao;

import com.springboot6.springboot6.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    User getUserById (Integer id);

    void addUser(User user);
    void delete(Integer id);
    void updateUser (Integer id, User updateUser);
}
