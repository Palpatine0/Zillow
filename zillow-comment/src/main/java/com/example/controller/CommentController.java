package com.example.controller;


import com.example.service.CommentService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ZillowResult addComment(String orderId, String commentContent) {
        return commentService.addComment(orderId, commentContent);
    }

    @GetMapping("/getCommentsByItemId")
    public ZillowResult getCommentsByItemId(@RequestParam(value = "itemId") String itemId, int page, @RequestParam(defaultValue = "5") int rows) {
        return commentService.getCommentsByItemId(itemId, page, rows);
    }

}
