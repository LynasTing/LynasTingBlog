package com.lynas.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
// 开启链式编程
@Accessors(chain = true)
// 空参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
public class MenuTreeVo {
  // 菜单ID
  private Long id;

  // 父菜单ID
  private Long parentId;

  // 菜单名
  private String label;

  // 子集
  private List children;
}
