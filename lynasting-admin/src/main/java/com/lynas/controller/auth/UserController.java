package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.auth.UserEditVo;
import com.lynas.domain.dto.auth.UserPageDto;
import com.lynas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  /**
   * 新增用户
   */
  @PostMapping("/add")
  public R addUer(@RequestBody UserEditVo arg) {
    return userService.addUser(arg);
  }

}
