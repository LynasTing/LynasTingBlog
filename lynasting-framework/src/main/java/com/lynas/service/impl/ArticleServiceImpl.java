package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Article;
import com.lynas.domain.entity.Category;
import com.lynas.domain.vo.ArticleDetailVo;
import com.lynas.domain.vo.ArticleListVo;
import com.lynas.domain.vo.PageVo;
import com.lynas.mapper.ArticleMapper;
import com.lynas.service.ArticleService;
import com.lynas.service.CategoryService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.domain.vo.HotArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
  @Autowired
  private CategoryService categoryService;

  @Override
  public ResponseResult getHot() {
    // 查询热门文章，封装成ResponseResult返回
    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    // 必须是已发布的
    queryWrapper.eq(Article::getStatus, SystemConst.ARTICLE_STATUS_NORMAL);
    // 按照浏览量降序
    queryWrapper.orderByDesc(Article::getViewCount);
    // 只查10条
    Page<Article> page = new Page(1, 10);
    page(page, queryWrapper);
    List<Article> records = page.getRecords();
    // bean拷贝
    List<HotArticleVo> hotArticleVos = BeanCopyUtils.beanListCopy(records, HotArticleVo.class);
    return ResponseResult.okResult(hotArticleVos);
  }

  @Override
  public ResponseResult getList(Long categoryId, Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
    // 如果有categoryId,查询的时候就要加上，下列只有第一个参数为true，才会加上后面两个参数进行查询
    articleWrapper.eq(Objects.isNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
    // 已发布的文章
    articleWrapper.eq(Article::getStatus, SystemConst.ARTICLE_STATUS_NORMAL);
    // isTop降序
    articleWrapper.orderByDesc(Article::getIsTop);
    // 分页
    Page<Article> page = new Page<>(pageNum, pageSize);
    page(page, articleWrapper);

    // 查询categoryName
    List<Article> articles = page.getRecords();
    articles.stream()
      .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
      .collect(Collectors.toList());

    // 封装出需返回的字段
    List<ArticleListVo> articleListVos = BeanCopyUtils.beanListCopy(articles, ArticleListVo.class);

    // 再次封装成 数组+total 的对象
    return ResponseResult.okResult(new PageVo(articleListVos, page.getTotal()));
  }

  public ResponseResult getDetail(Long id) {
    // 根据id查询文章
    Article article = getById(id);
    // 封装VO
    ArticleDetailVo articleDetailVo = BeanCopyUtils.beanCopy(article, ArticleDetailVo.class);
    // 先拿到分类id
    Long categoryId = articleDetailVo.getCategoryId();
    // 根据分类id查询分类名
    Category byId = categoryService.getById(categoryId);
    if(byId != null) {
      articleDetailVo.setCategoryName(byId.getName());
    }
    // 封装响应返回
    return ResponseResult.okResult(articleDetailVo);
  }
}
