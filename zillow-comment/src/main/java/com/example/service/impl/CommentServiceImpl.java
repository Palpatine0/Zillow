package com.example.service.impl;

import com.example.dao.CommentDao;
import com.example.dao.OrderDao;
import com.example.dao.UserDao;
import com.example.entity.Comment;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.service.CommentService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;


    @Override
    public BaseResult addComment(String orderId, String commentContent) {
        if (orderId == null) {
            return BaseResult.error("OrderId must not be null");
        }
        try {
            Order order = orderDao.getOrderByOrderId(orderId);
            if (order == null) {
                return BaseResult.error("No order found for the given orderId: " + orderId);
            }

            User user = userDao.getUserById(order.getUserId());
            if (user == null) {
                return BaseResult.error("No user found for the given userId: " + order.getUserId());
            }

            Comment comment = new Comment();
            comment.setUsername(user.getUsername());
            comment.setComment(commentContent);
            comment.setItemId(order.getItemId());
            comment.setStar(3);
            commentDao.saveComment(comment);
            return BaseResult.ok("Comment added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error("Comment add failed");
        }
    }

    @Override
    public BaseResult getCommentsByItemId(String itemId, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("itemId").is(itemId));
        query.with(PageRequest.of(page, rows));

        List<Comment> commentList = commentDao.findCommentsByItemId(query);
        BaseResult result = BaseResult.ok(commentList);
        long count = commentList == null ? 0 : commentList.size();
        long totalPages = ((count % rows == 0) ? (count / rows) : (count / rows + 1));
        result.setHasMore((page + 1) < totalPages ? true : false);
        return result;
    }
}
