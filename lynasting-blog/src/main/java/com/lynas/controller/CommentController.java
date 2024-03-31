package com.lynas.controller;


import com.lynas.domain.ResponseResult;
import com.lynas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping("list")
  public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
    return commentService.commentList(articleId, pageNum, pageSize);
  }
}
