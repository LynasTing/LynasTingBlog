package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.entity.User;
import com.lynas.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

  @Autowired
  private AdminLoginService adminLoginService;

  /**
   * 登录
   */
  @PostMapping("/user/login")
  public R login(@RequestBody User user) {
    return adminLoginService.login(user);
  }

  /**
   * 获取用户信息及权限
   */
  // @GetMapping("/getInfo")
  // public R getInfo() {
  //
  // }

}
