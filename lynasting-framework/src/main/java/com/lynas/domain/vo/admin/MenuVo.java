package com.lynas.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
// 开启链式编程
@Accessors(chain = true)
// 空参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
public class MenuVo {
  // 组件路径
  private String component;

  // 菜单图标
  private String icon;

  // 菜单ID
  private Long id;

  // 是否为外链（0是 1否）
  private Integer isFrame;

  // 菜单名称
  private String menuName;

  // 菜单类型（M目录 C菜单 F按钮）
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
