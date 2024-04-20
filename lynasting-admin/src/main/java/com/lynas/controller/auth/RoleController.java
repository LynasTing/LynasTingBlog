package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.auth.RoleAddDto;
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

  /**
   * 新增角色
   */
  @PostMapping("/add")
  public R addRole(@RequestBody RoleAddDto arg) {
    return roleService.addRole(arg);
  }

  /**
   * 角色回显
   */
  @GetMapping("/echo/{id}")
  public R echoRole(@PathVariable("id") Long id) {
    return roleService.echoRole(id);
  }

  /**
   * 修改角色
   */
  @PutMapping("/put")
  public R putRole(@RequestBody RoleAddDto arg) {
    return roleService.putRole(arg);
  }

  /**
   * 删除角色
   */
  @DeleteMapping("/del/{id}")
  public R delRole(@PathVariable("id") Long id) {
    return roleService.delRole(id);
  }

  /**
   * 查询所有角色字典
   */
  @GetMapping("/get")
  public R getAllRole() {
    return roleService.getAllRole();
  }
}
