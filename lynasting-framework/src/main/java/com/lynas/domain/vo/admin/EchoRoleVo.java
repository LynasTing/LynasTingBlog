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
public class EchoRoleVo {
  // 角色ID
  private Long id;
  // 角色名称
  private String roleName;
  // 角色权限字符串
  private String roleKey;
  // 显示顺序
  private Integer roleSort;
  // 角色状态（1正常 0停用）
  private Integer status;
  // 备注
  private String remark;
}
