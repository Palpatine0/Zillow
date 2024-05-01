package com.example.dao.impl;

import com.example.dao.CommentDao;
import com.example.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveComment(Comment comment) {
        mongoTemplate.save(comment);
    }

    @Override
    public List<Comment> getCommentByItemId(Query query) {
        return mongoTemplate.find(query,Comment.class);
    }
}
