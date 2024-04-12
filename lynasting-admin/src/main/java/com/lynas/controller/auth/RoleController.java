package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.auth.RolePageDto;
import com.lynas.domain.dto.auth.RoleStatusDto;
import com.lynas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {
  @Autowired
  private RoleService roleService;

  /**
   * 角色列表分页
   */
  @GetMapping("/list")
  public R pageRole(@ModelAttribute RolePageDto arg) {
    return roleService.pageRole(arg);
  }

  /**
   * 修改角色状态
   */
  @PutMapping("/status")
  public R changeStatus(@RequestBody RoleStatusDto arg) {
    return roleService.changeStatus(arg);
  }
}
