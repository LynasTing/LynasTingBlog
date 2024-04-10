package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.entity.ArticleTag;

import java.util.List;

/**
 * 文章标签关联表(ArticleTag)表服务接口
 *
 * @author LynasTing
 * @since 2024-04-07 15:30:19
 */
public interface ArticleTagService extends IService<ArticleTag> {

  List<Integer> getTagIdByArticleId(Long id);
}
