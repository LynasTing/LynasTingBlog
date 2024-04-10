package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.dto.ContentArticleDto;
import com.lynas.domain.dto.PageArticleDto;
import com.lynas.domain.vo.admin.EchoArticleDetailVo;
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

  /**
   * 查询文章详情
   */
  @GetMapping("/{id}")
  public R<EchoArticleDetailVo> getArticleDetail(@PathVariable long id) {
    return articleService.getArticleDetail(id);
  }

  /**
   * 修改文章
   */
  @PutMapping("/put")
  public R putArticle(@RequestBody ContentArticleDto arg) {
    return articleService.putArticle(arg);
  }

  /**
   * 删除文章
   */
  @DeleteMapping("/{id}")
  public R delArticle(@PathVariable Long id) {
    return articleService.delArticle(id);
  }
}
