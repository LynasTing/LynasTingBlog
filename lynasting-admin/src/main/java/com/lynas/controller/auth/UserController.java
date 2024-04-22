package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.auth.UserAddDto;
import com.lynas.domain.dto.auth.UserEditDto;
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
  public R addUer(@RequestBody UserAddDto arg) {
    return userService.addUser(arg);
  }

  /**
   * 删除用户
   */
  @DeleteMapping("/del/{id}")
  public R delUser(@PathVariable Long id) {
    return userService.delUser(id);
  }

  /**
   * 修改回显
   */
  @GetMapping("/echo/{id}")
  public R echoUser(@PathVariable Long id) {
    return userService.echoUser(id);
  }

  /**
   * 修改用户
   */
  @PutMapping("/edit")
  public R editUser(@RequestBody UserEditDto arg) {
    return userService.editUser(arg);
  }
}
