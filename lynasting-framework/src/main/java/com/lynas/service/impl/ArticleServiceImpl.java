package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;
import com.lynas.mapper.ArticleMapper;
import com.lynas.service.ArticleService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.domain.vo.HotArticleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
  @Override
  public ResponseResult getHot() {
    // 查询热门文章，封装成ResponseResult返回
    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    // 必须是已发布的
    queryWrapper.eq(Article::getStatus, SystemConst.ARTICLE_STATUS_NORMAL);
    // 按照浏览量降序
    queryWrapper.orderByDesc(Article::getViewCount);
    // 只查10条
    Page<Article> page = new Page(1, 10   );
    page(page, queryWrapper);
    List<Article> records = page.getRecords();
    // bean拷贝
    List<HotArticleVo> hotArticleVos = BeanCopyUtils.beanListCopy(records, HotArticleVo.class);
    return ResponseResult.okResult(hotArticleVos);
  }
}
