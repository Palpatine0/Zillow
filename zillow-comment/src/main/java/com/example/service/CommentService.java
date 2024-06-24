package com.example.service;

import com.example.vo.BaseResult;

public interface CommentService {
    BaseResult addComment(String orderId, String commentContent);

    BaseResult getCommentsByItemId(String itemId, int page, int rows);
}
