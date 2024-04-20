package com.lynas.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
// 开启链式编程
@Accessors(chain = true)
// 空参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
public class RoleGetVo {
  // 角色ID
  private Long id;
  // 角色名称
  private String roleName;
  // 角色状态（1正常 0停用）
  private Integer status;
}
