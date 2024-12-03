package com.example.dao;

import com.example.entity.Comment;
import com.example.entity.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

public interface CommentDao {
    List<Comment> findCommentsByCriteria(Map<String, Object> criteria);
}
