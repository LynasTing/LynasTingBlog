package com.lynas.domain.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页查询角色")
public class RolePageDto {
  @NotNull
  private Long pageNum;

  @NotNull
  private Long pageSize;

  @ApiModelProperty(notes = "角色名称")
  private String roleName;

  @ApiModelProperty(notes = "角色状态（1正常 0停用）")
  private Integer status;
}
