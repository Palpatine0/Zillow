package com.example.dao;

import com.example.entity.Comment;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CommentDao {
    void saveComment(Comment comment);
    List<Comment> findCommentsByItemId(Query query);
}
