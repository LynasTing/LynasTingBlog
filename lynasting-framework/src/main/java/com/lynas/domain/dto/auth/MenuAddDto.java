package com.lynas.domain.dto.auth;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增/修改菜单")
public class MenuAddDto {

  // 组件路径
  private String component;

  // 菜单图标
  private String icon;

  // 菜单ID
  private Long id;

  // 是否为外链（0是 1否）
  private Integer isFrame;

  // 菜单名称
  @NotNull
  private String menuName;

  // 菜单类型（M目录 C菜单 F按钮）
  @NotNull
  private String menuType;

  // 显示顺序
  private Integer orderNum;

  // 父菜单ID
  private Long parentId;

  // 路由地址
  private String path;

  // 权限标识
  private String perms;

  // 备注
  private String remark;

  // 菜单状态（1正常 0停用）
  private Integer status;

  // 菜单状态（1显示 0隐藏）
  private Integer visible;
}
