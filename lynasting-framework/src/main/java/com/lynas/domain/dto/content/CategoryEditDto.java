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
@ApiModel(description = "新增、修改分类")
public class CategoryEditDto {
  @NotNull
  @ApiModelProperty(notes = "分类名")
  private String name;

  @NotNull
  @ApiModelProperty(notes = "描述")
  private String description;

  @NotNull
  @ApiModelProperty(notes = "状态 1:正常, 0禁用")
  private int status;
}
