package com.lynas.controller;

import com.lynas.domain.ResponseResult;
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
  public ResponseResult getUserInfo() {
    return userService.getUserInfo();
  }

  /**
   * 更新用户信息
   */
  @PutMapping("/putInfo")
  public ResponseResult putUserInfo(@RequestBody User user) {
    return userService.putUserInfo(user);
  }

  /**
   * 注册
   */
  @PostMapping("/register")
  public ResponseResult register(@RequestBody User user) {
    return userService.register(user);
  }
}
