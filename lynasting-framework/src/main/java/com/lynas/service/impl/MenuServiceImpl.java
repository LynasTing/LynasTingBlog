package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.entity.Menu;
import com.lynas.mapper.MenuMapper;
import com.lynas.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-04 19:04:40
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  /**
   * 查询用户所拥有的权限，要求是所有菜单类型为C或F的、状态正常的、未被删除的
   */
  @Override
  public List<String> selectPermsById(Long id) {
    // 如果用户id为1代表是超级管理员，返回所有可用权限
    if(id == 1L) {
      LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
      // 使用 LambdaQueryWrapper.in方法，可以查多个条件
      wrapper.in(Menu::getMenuType, SystemConst.MENU_TYPE_C, SystemConst.MENU_TYPE_F);
      wrapper.eq(Menu::getStatus, SystemConst.MENU_STATUS_NORMAL);
      List<Menu> list = list(wrapper);
      List<String> menus = list.stream()
        .map(Menu::getPerms)
        .collect(Collectors.toList());
      return menus;
    }
    return getBaseMapper().selectPermsById(id);
  }
}
