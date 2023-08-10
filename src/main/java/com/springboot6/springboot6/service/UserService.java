package com.springboot6.springboot6.service;

import com.springboot6.springboot6.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById (Integer id);
    void addUser(User user);
    void delete(Integer id);
    void updateUser (Integer id, User updateUser);;
}
