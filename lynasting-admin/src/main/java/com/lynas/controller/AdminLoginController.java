package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.entity.LoginUser;
import com.lynas.domain.entity.Menu;
import com.lynas.domain.entity.User;
import com.lynas.domain.vo.AdminUserInfoVo;
import com.lynas.domain.vo.RoutesVo;
import com.lynas.domain.vo.UserInfoVo;
import com.lynas.service.AdminLoginService;
import com.lynas.service.MenuService;
import com.lynas.service.RoleService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminLoginController {

  /**
   * login
   */
  @Autowired
  private AdminLoginService adminLoginService;

  /**
   * menu
   */
  @Autowired
  private MenuService menuService;

  /**
   * role
   */
  @Autowired
  private RoleService roleService;

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
  @GetMapping("/getInfo")
  public R getInfo() {
    // 获取当前登录的用户
    LoginUser loginUser = SecurityUtils.getLoginUser();

    // 根据用户id查权限信息
    List<String> perms = menuService.selectPermsById(loginUser.getUser().getId());

    // 根据用户id查角色信息
    List<String> roles = roleService.selectRolesById(loginUser.getUser().getId());

    // 获取用户信息
    UserInfoVo userInfoVo = BeanCopyUtils.beanCopy(loginUser.getUser(), UserInfoVo.class);

    // 封装数据返回
    return R.okResult(new AdminUserInfoVo(perms, roles, userInfoVo));
  }

  /**
   * 获取动态路由
   */
  @GetMapping("/getRouters")
  public R<RoutesVo> getRouters() {
    Long id = SecurityUtils.getUserId();

    // 通过用户id查到树形的menus
    List<Menu> menus = menuService.selectMenuById(id);
    // 返回
    return R.okResult(menus);

  }

}
