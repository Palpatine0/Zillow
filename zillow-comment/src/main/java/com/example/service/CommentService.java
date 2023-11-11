package com.example.service;

import com.example.vo.ZillowResult;

public interface CommentService {
    ZillowResult addComment(String orderId, String comment);

    ZillowResult getCommentByItemID(String itemId, int page, int rows);
}
