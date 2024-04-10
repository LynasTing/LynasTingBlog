package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.mapper.ArticleTagMapper;
import com.lynas.domain.entity.ArticleTag;
import com.lynas.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-07 15:30:20
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {


  @Autowired
  private ArticleTagMapper articleTagMapper;

  /**
   * 用文章id查出标签id
   */
  public List<Integer> getTagIdByArticleId(Long id) {
    LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ArticleTag::getArticleId, id);
    List<ArticleTag> articleTags = articleTagMapper.selectList(wrapper);
    List<Integer> collect = articleTags.stream()
      .map(item -> item.getTagId().intValue())
      .collect(Collectors.toList());
    return collect;
  }
}
