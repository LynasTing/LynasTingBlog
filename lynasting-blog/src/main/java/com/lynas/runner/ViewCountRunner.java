package com.lynas.runner;

import com.lynas.domain.entity.Article;
import com.lynas.mapper.ArticleMapper;
import com.lynas.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
  @Autowired
  private ArticleMapper articleMapper;

  @Autowired
  private RedisCache redisCache;

  @Override
  public void run(String... args) throws Exception {
    // 查询博客信息, id 和 viewCount
    List<Article> articles = articleMapper.selectList(null);
    Map<String, Integer> viewCountMap = articles.stream()
      .collect(Collectors.toMap(item -> item.getId().toString(), article -> article.getViewCount().intValue()));

    // 存储到redis
    redisCache.setCacheMap("article:viewCount", viewCountMap);
  }
}
