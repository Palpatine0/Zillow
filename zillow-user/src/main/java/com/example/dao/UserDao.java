package com.example.dao;

import com.example.entity.User;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username);

    void saveUser(User user);

    void deleteUserById(String id);

    List<User> selectUsers(Query query);

}
