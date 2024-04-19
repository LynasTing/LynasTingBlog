package com.lynas.domain.vo.admin;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class UserPageVo {
  // 头像
  private String avatar;

  // 创建时间
  private Date createTime;

  // 邮箱
  private String email;

  // 主键
  private Long id;

  // 昵称
  private String nickname;

  //手机号
  private String phoneNum;

  // 用户性别（1男，2女，3未知）
  private int sex;

  // 账号状态（0正常 1停用）
  private int status;

  // 更新时间
  private Date updateTime;

  // 用户名
  private String username;
}
