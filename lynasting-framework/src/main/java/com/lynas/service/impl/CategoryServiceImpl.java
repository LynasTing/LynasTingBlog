package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryEditDto;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.domain.entity.Article;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.content.CategoryPageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.CategoryMapper;
import com.lynas.domain.entity.Category;
import com.lynas.service.ArticleService;
import com.lynas.service.CategoryService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.domain.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
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
    Set<Integer> collect1 = articleList.stream()
      .map(article -> article.getCategoryId())
      .collect(Collectors.toSet());
    // 查询分类表
    List<Category> categories = listByIds(collect1);
    // 过滤出状态是正常的分类
    List<Category> collect = categories.stream()
      .filter(item -> item.getStatus() == SystemConst.CATEGORY_STATUS_NORMAL)
      .collect(Collectors.toList());
    // 封装成VO
    List<CategoryVo> categoryVos = BeanCopyUtils.beanListCopy(collect, CategoryVo.class);
    // 返回
    return R.okResult(categoryVos);
  }

  /**
   * 查询所有分类
   */
  @Override
  public List<CategoryVo> getAll() {
    LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Category::getStatus, SystemConst.CATEGORY_STATUS_NORMAL);
    List<Category> list = list(wrapper);
    List<CategoryVo> categoryVos = BeanCopyUtils.beanListCopy(list, CategoryVo.class);
    return categoryVos;
  }

  /**
   * 分页查询分类
   */
  @Override
  public R pageCategory(CategoryPageDto arg) {
    LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
    if(!Objects.isNull(arg.getStatus())) {
      wrapper.eq(Category::getStatus, arg.getStatus());
    }
    wrapper.like(StringUtils.hasText(arg.getName()), Category::getName, arg.getName());
    Page<Category> page = new Page(arg.getPageNum(), arg.getPageSize());
    page(page, wrapper);
    List<CategoryPageVo> categoryPageVos = BeanCopyUtils.beanListCopy(page.getRecords(), CategoryPageVo.class);
    return R.okResult(new PageVo(categoryPageVos, page.getTotal()));
  }

  /**
   * 新增分类
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R addCategory(CategoryEditDto arg) {
    try {
      Category category = BeanCopyUtils.beanCopy(arg, Category.class);
      save(category);
      return R.okResult();
    }catch (Exception e) {
      e.printStackTrace();
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 分类回显
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R echoCategory(Long id) {
    try {
      if (Objects.isNull(id)) {
        throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
      }
      Category byId = getById(id);
      CategoryPageVo categoryPageVo = BeanCopyUtils.beanCopy(byId, CategoryPageVo.class);
      return R.okResult(categoryPageVo);
    } catch (Exception e) {
      e.printStackTrace();
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 更新分类
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R putCategory(CategoryEditDto arg) {
    try {
      if (Objects.isNull(arg.getId())) {
        throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
      }
      Category category = BeanCopyUtils.beanCopy(arg, Category.class);
      getBaseMapper().updateById(category);
      return R.okResult();
    }catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 删除分类
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R delCategory(Long id) {
    try {
      if (Objects.isNull(id)) {
        throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
      }
      Category byId = getById(id);
      getBaseMapper().deleteById(byId);
      return R.okResult();
    }catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
    return null;
  }
}
