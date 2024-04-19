package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.auth.UserPageDto;
import com.lynas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class UserController {
  @Autowired
  UserService userService;

  /**
   * 用户分页列表
   */
  @GetMapping("/list")
  public R pageUser(@ModelAttribute UserPageDto arg) {
    return userService.pageUser(arg);
  }

}
