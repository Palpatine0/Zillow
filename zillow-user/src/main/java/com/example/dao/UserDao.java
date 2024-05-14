package com.example.dao;

import com.example.entity.User;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UserDao {


    void saveUser(User user);

    void removeUserById(String id);

    List<User> findUsers(Query query);

    User findUserByUsername(String username);

    User findUserById(String id);

}
