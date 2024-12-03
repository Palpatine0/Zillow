package com.example.dao.impl;

import com.example.dao.CommentDao;
import com.example.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findCommentsByCriteria(Map<String, Object> criteria) {
        Query query = new Query();

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                Map<String, Object> operatorMap = (Map<String, Object>) value;
                Criteria criteriaObj = Criteria.where(key);

                for (Map.Entry<String, Object> opEntry : operatorMap.entrySet()) {
                    String operator = opEntry.getKey();
                    Object opValue = opEntry.getValue();

                    switch (operator) {
                        case "$lt":
                            criteriaObj.lt(opValue);
                            break;
                        case "$lte":
                            criteriaObj.lte(opValue);
                            break;
                        case "$gt":
                            criteriaObj.gt(opValue);
                            break;
                        case "$gte":
                            criteriaObj.gte(opValue);
                            break;
                        case "$ne":
                            criteriaObj.ne(opValue);
                            break;
                        case "$in":
                            criteriaObj.in((List<?>) opValue);
                            break;
                        case "$exists":
                            criteriaObj.exists((Boolean) opValue);
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported operator: " + operator);
                    }
                }
                query.addCriteria(criteriaObj);
            } else {
                query.addCriteria(Criteria.where(key).is(value));
            }
        }

        return mongoTemplate.find(query, Comment.class);
    }
}