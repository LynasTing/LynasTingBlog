package com.lynas.controller;

import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;
import com.lynas.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/getHot")
  public ResponseResult getHot() {
    return articleService.getHot();
  }


  @GetMapping("/list")
  public ResponseResult List(Long categoryId, Integer pageNum, Integer pageSize) {
    return articleService.getList(categoryId, pageNum, pageSize);
  }
}
