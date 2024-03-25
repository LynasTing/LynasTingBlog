package com.lynas.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {
  private Long id;
  // 标题
  private String title;
  // 文章摘要
  private String summary;
  // 缩略图
  private String thumbnail;
  // 分类名称
  private String categoryName;
  // 是否置顶（0否，1是）
  private String isTop;
  // 状态（0已发布，1草稿）
  private String status;
  // 访问量
  private Long viewCount;
  // 创建时间
  private Date createTime;
}
