package com.example.dao;

import com.example.entity.User;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User getUserByUsername(String username);

    List<User> selectUsers(Query query);

}
