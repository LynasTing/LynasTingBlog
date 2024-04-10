package com.lynas.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
// 开启链式编程
@Accessors(chain = true)
// 空参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
public class EchoArticleDetailVo {
  private Long id;
  // 标题
  private String title;
  // 文章摘要
  private String summary;
  // 缩略图
  private String thumbnail;
  // 内容
  private String content;
  // 分类id
  private int categoryId;
  // 标签
  private List<Integer> tags;
  // 是否允许评论 1是 0否
  private int isComment;
  // 是否置顶（0否，1是）
  private int isTop;

}
