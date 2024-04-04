package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.entity.Article;
import com.lynas.mapper.CategoryMapper;
import com.lynas.domain.entity.Category;
import com.lynas.service.ArticleService;
import com.lynas.service.CategoryService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.domain.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-25 10:43:59
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

  /**
   * 在分类表中无法直接调用文章表，需要先注入
   */
  @Autowired
  private ArticleService articleService;

  @Override
  public R getList() {
    // 查询文章表，状态为已发布的文章
    LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
    articleWrapper.eq(Article::getStatus, SystemConst.ARTICLE_STATUS_NORMAL);
    List<Article> articleList = articleService.list(articleWrapper);
    // 获取文章的分类id，并且去重
    Set<Long> categoryIds = articleList.stream()
      .map(article -> article.getCategoryId())
      .collect(Collectors.toSet());
    // 查询分类表
    List<Category> categories = listByIds(categoryIds);
    // 过滤出状态是正常的分类
    List<Category> collect = categories.stream()
      .filter(item -> item.getStatus() == SystemConst.CATEGORY_STATUS_NORMAL)
      .collect(Collectors.toList());
    // 封装成VO
    List<CategoryVo> categoryVos = BeanCopyUtils.beanListCopy(collect, CategoryVo.class);
    // 返回
    return R.okResult(categoryVos);
  }
}
