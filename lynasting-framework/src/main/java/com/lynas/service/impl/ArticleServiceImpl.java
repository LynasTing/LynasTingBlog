package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.entity.Article;
import com.lynas.mapper.ArticleMapper;
import com.lynas.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
