package com.lynas.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询菜单")
public class MenuQueryDto {

  @ApiModelProperty(notes = "1正常 0停用")
  private Integer status;

  @ApiModelProperty(notes = "菜单名")
  private String menuName;
}
