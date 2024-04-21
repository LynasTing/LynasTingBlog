package com.lynas.domain.vo.admin;

import com.lynas.domain.entity.User;
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
public class UserEchoVo {
  // 用户关联的角色id列表
  private List<Integer> roleIds;

  // 所有角色的列表
  private List<RoleGetVo> roles;

  // 用户信息
  private User user;
}
