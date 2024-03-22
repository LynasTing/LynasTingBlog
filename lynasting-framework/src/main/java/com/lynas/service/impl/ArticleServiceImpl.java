package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;
import com.lynas.mapper.ArticleMapper;
import com.lynas.service.ArticleService;
import com.lynas.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
  @Override
  public ResponseResult getHot() {
    // 查询热门文章，封装成ResponseResult返回
    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    // 必须是已发布的
    queryWrapper.eq(Article::getStatus, 1);
    // 按照浏览量降序
    queryWrapper.orderByDesc(Article::getViewCount);
    // 只查10条
    Page<Article> page = new Page(1, 10   );
    page(page, queryWrapper);
    // bean拷贝
    List<HotArticleVo> articleVos = new ArrayList<>();
    List<Article> records = page.getRecords();
    for (Article record : records) {
      HotArticleVo vo = new HotArticleVo();
      BeanUtils.copyProperties(record, vo);
      articleVos.add(vo);
    }
    return ResponseResult.okResult(articleVos);
  }
}
