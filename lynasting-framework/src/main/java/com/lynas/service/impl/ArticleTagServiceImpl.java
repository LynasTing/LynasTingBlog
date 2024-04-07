package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.mapper.ArticleTagMapper;
import com.lynas.domain.entity.ArticleTag;
import com.lynas.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-07 15:30:20
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
