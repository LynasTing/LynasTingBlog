package com.lynas.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesVo {
  // 菜单ID
  private Long id;
  // 菜单名称
  private String menuName;
  // 父菜单ID
  private Long parentId;
  // 显示顺序
  private Integer orderNum;
  // 路由地址
  private String path;
  // 组件路径
  private String component;
  // 是否为外链（0是 1否）
  private Integer isFrame;
  // 菜单类型（M目录 C菜单 F按钮）
  private String menuType;
  // 菜单状态（1显示 0隐藏）
  private Integer visible;
  // 菜单状态（1正常 0停用）
  private Integer status;
  // 权限标识
  private String perms;
  // 菜单图标
  private String icon;
  // 创建时间
  private Date createTime;

}
