package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.dto.ContentArticleDto;
import com.lynas.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
  @Autowired
  private ArticleService articleService;

  @PostMapping("/add")
  public R addArticle(@RequestBody ContentArticleDto arg) {
    return articleService.addArticle(arg);
  }
}
