package com.lynas.domain.vo;

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
public class AdminUserInfoVo {

  private List<String> permissions;

  private List<String> roles;

  private UserInfoVo user;
}
