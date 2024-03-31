package com.lynas.controller;


import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Comment;
import com.lynas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping("list")
  public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
    return commentService.commentList(articleId, pageNum, pageSize);
  }

  @PostMapping("/reply")
  public ResponseResult commentReply(@RequestBody Comment comment) {
    return commentService.commentReply(comment);
  }
}
