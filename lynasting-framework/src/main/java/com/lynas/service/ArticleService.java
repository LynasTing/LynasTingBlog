package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;

public interface ArticleService extends IService<Article> {
  ResponseResult getHot();

  ResponseResult getList(Long categoryId, Integer pageNum, Integer pageSize);
}
