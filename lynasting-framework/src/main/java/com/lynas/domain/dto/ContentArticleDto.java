package com.lynas.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增博文dto")
public class ContentArticleDto {

  @ApiModelProperty(notes = "标题 新增不传， 修改传")
  private Long id;

  @ApiModelProperty(notes = "标题")
  private String title;

  @ApiModelProperty(notes = "缩略图")
  private String thumbnail;

  @ApiModelProperty(notes = "是否置顶 0否，1是")
  private int isTop;

  @ApiModelProperty(notes = "是否允许评论 1是，0否")
  private int isComment;

  @ApiModelProperty(notes = "文章内容")
  private String content;

  @ApiModelProperty(notes = "标签")
  private List<Long> tags;

  @ApiModelProperty(notes = "所属分类id")
  private int categoryId;

  @ApiModelProperty(notes = "文章摘要")
  private String summary;

  @ApiModelProperty(notes = "0已发布，1草稿")
  private int status;

  @TableField(fill = FieldFill.INSERT)
  private Long createBy;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateBy;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
