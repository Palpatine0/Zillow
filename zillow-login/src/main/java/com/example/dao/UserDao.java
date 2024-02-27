package com.example.dao;

import com.example.entity.User;

public interface UserDao {
    void saveUser(User user);

    User getUserByUsername(String username);

}
