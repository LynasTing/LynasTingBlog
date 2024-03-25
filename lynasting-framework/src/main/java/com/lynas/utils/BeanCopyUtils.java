package com.lynas.utils;

import com.lynas.domain.entity.Article;
import com.lynas.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

  /**
   * 复制对象
   */
  public static <T> T beanCopy(Object source, Class<T> type) {
    // 最终要return出的数据
    T result = null;
    try {
      result = type.newInstance();
      // 实现属性copy
      BeanUtils.copyProperties(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /***
   * 复制数组
   */
  public static <K, T> List<T> beanListCopy(List<K> list, Class<T> type) {
    System.out.println("list" + list);
    System.out.println("type" + type);
    return list.stream()
      .map(item -> beanCopy(item, type))
      // 收集操作
      .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    Article article = new Article();
    article.setId(2L);
    article.setTitle("你好啊");
    HotArticleVo hotArticleVo = beanCopy(article, HotArticleVo.class);
    System.out.println(hotArticleVo);
  }
}
