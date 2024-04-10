package com.lynas.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文章分页查询")
public class PageArticleDto {
  /**
   * 页数
   */
  @NotNull
  private Long pageNum;

  /**
   * 条数
   */
  @NotNull
  private Long pageSize;

  /**
   * 标签名
   */
  private String title;

  /**
   * 备注
   */
  private String summary;
}
