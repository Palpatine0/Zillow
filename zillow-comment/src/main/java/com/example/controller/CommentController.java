package com.example.controller;


import com.example.service.CommentService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //    @PostMapping("/addComment")
    @PostMapping("/feelback")
    public ZillowResult addComment(String orderId, String commentContent) {
        return commentService.addComment(orderId, commentContent);
    }

    //    @GetMapping("/getComment")
    @GetMapping("/comment")
    public ZillowResult getCommentByItemId(@RequestParam(value = "id") String itemId, int page, @RequestParam(defaultValue = "5") int rows) {
        return commentService.getCommentByItemID(itemId, page, rows);
    }

}
