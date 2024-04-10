package com.lynas.controller.auth;

import com.lynas.domain.R;
import com.lynas.domain.dto.MenuQueryDto;
import com.lynas.domain.dto.auth.MenuAddDto;
import com.lynas.domain.vo.admin.MenuVo;
import com.lynas.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

  @Autowired
  private MenuService menuService;

  /**
   * 查询所有菜单
   */
  @GetMapping("/list")
  public R queryAllMenu(@ModelAttribute MenuQueryDto arg) {
    return menuService.queryAllMenu(arg);
  }

  /**
   * 新增菜单
   */
  @PostMapping("/add")
  public R addMenu(@RequestBody MenuAddDto arg) {
    return menuService.addMenu(arg);
  }

  /**
   * 菜单编辑回显
   */
  @GetMapping("/echo/{id}")
  public R echoMenu(@PathVariable("id") Long id) {
    return menuService.echoMenu(id);
  }

  /**
   * 菜单修改
   */
  @PutMapping("/put")
  public R putMenu(@RequestBody MenuAddDto arg) {
    return menuService.putMenu(arg);
  }

  /**
   * 删除菜单
   */
  @DeleteMapping("/delete/{menuId}")
  public R delMenu (@PathVariable("menuId") Long menuId) {
    return menuService.delMenu(menuId);
  }
}
