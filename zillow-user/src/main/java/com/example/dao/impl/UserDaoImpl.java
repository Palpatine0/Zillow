package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, User.class);
    }

    @Override
    public User getUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> selectUsers(Query query) {
        return mongoTemplate.find(query, User.class);
    }


}
