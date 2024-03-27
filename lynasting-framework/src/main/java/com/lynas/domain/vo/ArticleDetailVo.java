package com.lynas.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
  private Long id;
  // 标题
  private String title;
  // 分类id
  private Long categoryId;
  // 分类名称
  private String categoryName;
  // 是否允许评论 1是 0否
  private String isComment;
  // 是否置顶（0否，1是）
  private String isTop;
  // 内容
  private String content;
  // 状态（0已发布，1草稿）
  private String status;
  // 访问量
  private Long viewCount;
  // 创建时间
  private Date createTime;
}
