package com.example.controller;


import com.example.service.CommentService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public BaseResult addComment(String orderId, String commentContent) {
        return commentService.addComment(orderId, commentContent);
    }

    @GetMapping("/getCommentsByItemId")
    public BaseResult getCommentsByItemId(@RequestParam(value = "itemId") String itemId, int page, @RequestParam(defaultValue = "5") int rows) {
        return commentService.getCommentsByItemId(itemId, page, rows);
    }

}
