package com.lynas.controller;

import com.lynas.domain.ResponseResult;
import com.lynas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 用户信息
   */
  @GetMapping("/info")
  public ResponseResult info() {
    return userService.getUserInfo();
  }
}
