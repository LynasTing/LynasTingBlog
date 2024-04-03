package com.lynas.controller;

import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;
import com.lynas.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  /**
   * 获取热门文章
   */
  @GetMapping("/getHot")
  public ResponseResult getHot() {
    return articleService.getHot();
  }

  /**
   * 获取文章列表
   */
  @GetMapping("/list")
  public ResponseResult list(Long categoryId, Integer pageNum, Integer pageSize) {
    return articleService.getList(categoryId, pageNum, pageSize);
  }


  /**
   * 获取文章详情
   */
  @GetMapping("/{id}")
  public ResponseResult detail(@PathVariable("id") Long id) {
    return articleService.getDetail(id);
  }

  /**
   * 更新文章访问量
   */
  @PutMapping("/putViewCount/{id}")
  public ResponseResult putViewCount(@PathVariable("id") Long id) {
    return articleService.putViewCount(id);
  }
}
