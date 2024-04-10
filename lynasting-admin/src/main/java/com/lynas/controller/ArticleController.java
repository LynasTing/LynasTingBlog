package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.dto.ContentArticleDto;
import com.lynas.domain.dto.PageArticleDto;
import com.lynas.domain.vo.PageVo;
import com.lynas.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
  @Autowired
  private ArticleService articleService;

  @PostMapping("/add")
  public R addArticle(@RequestBody ContentArticleDto arg) {
    return articleService.addArticle(arg);
  }

  /**
   * 分页查询文章列表
   */
  @GetMapping("/page")
  public R<PageVo> pageArticle(@ModelAttribute PageArticleDto arg) {
    return articleService.pageArticle(arg);
  }
}
