package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.dto.ContentArticleDto;
import com.lynas.domain.dto.PageArticleDto;
import com.lynas.domain.entity.Article;
import com.lynas.domain.entity.ArticleTag;
import com.lynas.domain.entity.Category;
import com.lynas.domain.vo.ArticleDetailVo;
import com.lynas.domain.vo.admin.EchoArticleDetailVo;
import com.lynas.domain.vo.ArticleListVo;
import com.lynas.domain.vo.PageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.ArticleMapper;
import com.lynas.service.ArticleService;
import com.lynas.service.ArticleTagService;
import com.lynas.service.CategoryService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.domain.vo.HotArticleVo;
import com.lynas.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
  @Autowired
  private CategoryService categoryService;

  @Autowired
  private RedisCache redisCache;

  @Autowired
  private ArticleTagService articleTagService;

  @Override
  public R getHot() {
    // 查询热门文章，封装成R返回
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
    return R.okResult(hotArticleVos);
  }

  @Override
  public R getList(Long categoryId, Integer pageNum, Integer pageSize) {
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
    return R.okResult(new PageVo(articleListVos, page.getTotal()));
  }

  public R getDetail(Long id) {
    // 根据id查询文章
    Article article = getById(id);
    // 从redis中取出访问量
    Integer viewCount = redisCache.getCacheMapValue(SystemConst.REDIS_VIEW_COUNT_KEY, id.toString());
    article.setViewCount(viewCount.longValue());
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
    return R.okResult(articleDetailVo);
  }

  @Override
  public R putViewCount(Long id) {
    redisCache.incrementCacheMapValue(SystemConst.REDIS_VIEW_COUNT_KEY, id.toString(), 1);
    return R.okResult();
  }

  /**
   * 新增文章
   */
  @Override
  @Transactional
  public R addArticle(ContentArticleDto arg) {
    // 先保存
    Article article = BeanCopyUtils.beanCopy(arg, Article.class);
    save(article);

    // 再关联表
    List<ArticleTag> collects = arg.getTags().stream()
      .map(item -> new ArticleTag(article.getId(), item))
      .collect(Collectors.toList());

    articleTagService.saveBatch(collects);
    return R.okResult();
  }

  /**
   * 分页查询文章列表
   */

  @Override
  public R pageArticle(PageArticleDto arg) {
    LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

    wrapper.like(StringUtils.hasText(arg.getTitle()), Article::getTitle, arg.getTitle());
    wrapper.like(StringUtils.hasText(arg.getSummary()), Article::getTitle, arg.getSummary());

    Page<Article> page = new Page<>(arg.getPageNum(), arg.getPageSize());
    page(page, wrapper);

    List<ArticleListVo> articleListVos = BeanCopyUtils.beanListCopy(page.getRecords(), ArticleListVo.class);
    return R.okResult(new PageVo(articleListVos, page.getTotal()));
  }

  /**
   * 查询文章详情
   * @param id
   * @return
   */

  @Override
  public R<EchoArticleDetailVo> getArticleDetail(long id) {
    Article article = getById(id);
    List<Integer> tagIds = articleTagService.getTagIdByArticleId(id);
    article.setTags(tagIds);
    EchoArticleDetailVo articleListVo = BeanCopyUtils.beanCopy(article, EchoArticleDetailVo.class);
    return R.okResult(articleListVo);
  }

  /**
   * 更新文章
   */
  @Override
  public R putArticle(ContentArticleDto arg) {
    if(Objects.isNull(arg.getId())) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    // TODO 其它非空判断
    Article article = BeanCopyUtils.beanCopy(arg, Article.class);
    updateById(article);
    return R.okResult();
  }

  /**
   * 删除文章(逻辑删除)
   */
  @Override
  public R delArticle(Long id) {
    if(Objects.isNull(id)) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    Article article = getById(id);
    article.setDelFlag(1);
    getBaseMapper().deleteById(id);
    return R.okResult();
  }
}
