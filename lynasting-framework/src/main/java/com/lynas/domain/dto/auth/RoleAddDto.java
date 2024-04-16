package com.lynas.domain.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增/修改角色")
public class RoleAddDto {
  @ApiModelProperty(notes = "角色名称")
  private String roleName;

  @ApiModelProperty(notes = "角色权限字符串")
  private String roleKey;

  @ApiModelProperty(notes = "显示顺序")
  private Integer roleSort;

  @ApiModelProperty(notes = "角色状态（1正常 0停用）")
  private Integer status;

  @ApiModelProperty(notes = "备注")
  private String remark;

  @ApiModelProperty(notes = "菜单集")
  private List<Long> menuIds;
}
