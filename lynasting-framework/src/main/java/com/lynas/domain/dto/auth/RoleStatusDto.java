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
@ApiModel(description = "修改角色状态")
public class RoleStatusDto {
  @NotNull
  @ApiModelProperty(notes = "角色id")
  private String roleId;

  @NotNull
  @ApiModelProperty(notes = "角色状态（1正常 0停用）")
  private Integer status;
}
