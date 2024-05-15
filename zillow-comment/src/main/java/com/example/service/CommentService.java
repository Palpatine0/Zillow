package com.example.service;

import com.example.vo.ZillowResult;

public interface CommentService {
    ZillowResult addComment(String orderId, String commentContent);

    ZillowResult getCommentsByItemId(String itemId, int page, int rows);
}
