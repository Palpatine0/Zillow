package com.example.service.impl;

import com.example.dao.CommentDao;
import com.example.dao.OrderDao;
import com.example.dao.UserDao;
import com.example.entity.Comment;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.service.CommentService;
import com.example.vo.ZillowResult;
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


    @Value("${zillow.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;


    @Override
    public ZillowResult addComment(String orderId, String commentContent) {
        if (orderId == null) {
            return ZillowResult.error("OrderId must not be null");
        }
        try {
            Order order = orderDao.getOrderByOrderId(orderId);
            if (order == null) {
                return ZillowResult.error("No order found for the given orderId: " + orderId);
            }

            User user = userDao.getUserById(order.getUserId());
            if (user == null) {
                return ZillowResult.error("No user found for the given userId: " + order.getUserId());
            }

            Comment comment = new Comment();
            comment.setUsername(user.getUsername());
            comment.setComment(commentContent);
            comment.setItemId(order.getItemId());
            comment.setStar(3);
            commentDao.saveComment(comment);
            return ZillowResult.ok("Comment added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ZillowResult.error("Comment add failed");
        }
    }

    @Override
    public ZillowResult getCommentsByItemId(String itemId, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("itemId").is(itemId));
        query.with(PageRequest.of(page, rows));

        List<Comment> comments = commentDao.findCommentsByItemId(query);

        for (Comment comment : comments) {
            String username = comment.getUsername().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            comment.setUsername(username);
        }
        ZillowResult result = ZillowResult.ok(comments);


        long count = comments == null ? 0 : comments.size();
        long totalPages = ((count % rows == 0) ? (count / rows) : (count / rows + 1));

        if ((page + 1) < totalPages) {
            result.setHasMore(true);
        } else {
            result.setHasMore(false);
        }

        return result;
    }
}
