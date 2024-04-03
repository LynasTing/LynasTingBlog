package com.lynas.job;

import com.lynas.constants.SystemConst;
import com.lynas.domain.entity.Article;
import com.lynas.service.ArticleService;
import com.lynas.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PutViewCountJob {

  @Autowired
  private RedisCache redisCache;

  @Autowired
  private ArticleService articleService;

  @Scheduled(cron = "* 10 * * * ?")
  public void putViewCount() {
    // 获取redis中的浏览量
    Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConst.REDIS_VIEW_COUNT_KEY);
    List<Article> collect = cacheMap.entrySet()
      .stream()
      .map(item -> new Article(Long.valueOf(item.getKey()), item.getValue().longValue()))
      .collect(Collectors.toList());

    // 更新到数据库中
    articleService.updateBatchById(collect);
  }
}
