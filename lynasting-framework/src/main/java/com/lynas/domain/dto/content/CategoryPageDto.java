package com.lynas.domain.dto.content;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页查询分类列表")
public class CategoryPageDto {
  @NotNull
  private Long pageNum;

  @NotNull
  private Long pageSize;

  @ApiModelProperty(notes = "分类名称")
  private String name;

  @ApiModelProperty(notes = "账号状态（1正常 0停用）")
  private Integer status;
}
