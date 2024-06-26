package com.lynas.domain.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章表(Article)表实体类
 *
 * @author LynasTing
 * @since 2024-03-21 14:17:01
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lt_article")
@Accessors(chain = true)
public class Article {
    @TableId
    private Long id;
    // 标题
    private String title;
    // 文章内容
    private String content;
    // 文章摘要
    private String summary;

    // 所属分类id
    private int categoryId;

    @TableField(exist = false)
    private String categoryName;

    // 标签
    @TableField(exist = false)
    private List<Integer> tags;

    // 缩略图
    private String thumbnail;

    // 是否置顶（0否，1是）
    private int isTop;

    // 状态（0已发布，1草稿）
    private int status;
    // 访问量
    private Long viewCount;
    // 是否允许评论 1是，0否
    private int isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    // 删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer delFlag;

  public Article(Long id, long viewCount) {
      this.id = id;
      this.viewCount = viewCount;
  }
}
