package com.lynas.controller;

import com.lynas.annotation.SystemLog;
import com.lynas.domain.R;
import com.lynas.domain.entity.User;
import com.lynas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 获取用户信息
   */
  @GetMapping("/getInfo")
  public R getUserInfo() {
    return userService.getUserInfo();
  }

  /**
   * 更新用户信息
   */
  @PutMapping("/putInfo")
  @SystemLog(business = "更新用户信息")
  public R putUserInfo(@RequestBody User user) {
    return userService.putUserInfo(user);
  }

  /**
   * 注册
   */
  @PostMapping("/register")
  public R register(@RequestBody User user) {
    return userService.register(user);
  }
}
