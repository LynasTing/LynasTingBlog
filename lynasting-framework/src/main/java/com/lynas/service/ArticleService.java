package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.dto.ContentArticleDto;
import com.lynas.domain.dto.PageArticleDto;
import com.lynas.domain.entity.Article;
import com.lynas.domain.vo.admin.EchoArticleDetailVo;

public interface ArticleService extends IService<Article> {
  R getHot();

  R getList(Long categoryId, Integer pageNum, Integer pageSize);

  R getDetail(Long id);

  R putViewCount(Long id);

  R addArticle(ContentArticleDto arg);

  R pageArticle(PageArticleDto arg);

  R<EchoArticleDetailVo> getArticleDetail(long id);

  R putArticle(ContentArticleDto arg);

  R delArticle(Long id);
}
